pipeline {

    agent any

    tools {
        maven 'Maven 3.9'
    }

    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: [
                'full',
                'smoke',
                'regression',
                'unit',
                'integration'
            ],
            description: 'Select the TestNG suite to execute'
        )

        choice(
            name: 'ENVIRONMENT',
            choices: ['dev'],
            description: 'Target test environment available in the Docker CI stack'
        )
    }

    environment {
        ENV = "${params.ENVIRONMENT}"
        DEV_BASE_URL = 'http://enterprise-user-api:8081'
        DEV_DB_URL = 'jdbc:postgresql://enterprise-user-api-db:5432/automationdb'
    }

    options {
        skipDefaultCheckout(true)
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh '''
                    set -e
                    java -version
                    mvn -version
                '''
            }
        }

        stage('Verify Services') {
            steps {
                sh '''
                    set -e

                    echo "Resolving Docker services..."
                    getent hosts enterprise-user-api
                    getent hosts enterprise-user-api-db

                    echo "Checking API port..."
                    timeout 10 bash -c \
                      'until </dev/tcp/enterprise-user-api/8081; do sleep 1; done'

                    echo "Checking PostgreSQL port..."
                    timeout 10 bash -c \
                      'until </dev/tcp/enterprise-user-api-db/5432; do sleep 1; done'

                    echo "API and database services are reachable."
                '''
            }
        }

        stage('Execute Tests') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'enterprise-api-admin',
                        usernameVariable: 'AUTH_ADMIN_USERNAME',
                        passwordVariable: 'AUTH_ADMIN_PASSWORD'
                    ),
                    usernamePassword(
                        credentialsId: 'enterprise-database',
                        usernameVariable: 'DB_USERNAME',
                        passwordVariable: 'DB_PASSWORD'
                    )
                ]) {
                    sh '''
                        set +x

                        echo "Environment: $ENV"
                        echo "Suite: $TEST_SUITE"

                        case "$TEST_SUITE" in
                            smoke)
                                mvn clean test \
                                  -Dsurefire.suiteXmlFiles=smoke.xml
                                ;;
                            regression)
                                mvn clean test \
                                  -Dsurefire.suiteXmlFiles=regression.xml
                                ;;
                            unit)
                                mvn clean test \
                                  -Dsurefire.suiteXmlFiles=unit.xml
                                ;;
                            integration)
                                mvn clean test \
                                  -Dsurefire.suiteXmlFiles=integration.xml
                                ;;
                            full)
                                mvn clean test
                                ;;
                            *)
                                echo "Unsupported suite: $TEST_SUITE"
                                exit 1
                                ;;
                        esac
                    '''
                }
            }
        }
    }

    post {

        always {
            script {
                if (fileExists('target/surefire-reports')) {
                    junit(
                        testResults: 'target/surefire-reports/*.xml',
                        allowEmptyResults: false
                    )
                } else {
                    echo 'Surefire reports were not generated.'
                }

                if (fileExists('target/allure-results')) {
                    allure(
                        commandline: 'Allure',
                        includeProperties: false,
                        results: [[path: 'target/allure-results']]
                    )
                } else {
                    echo 'Allure results were not generated.'
                }
            }

            archiveArtifacts(
                artifacts: '''
                    target/surefire-reports/**,
                    target/allure-results/**
                ''',
                allowEmptyArchive: true,
                fingerprint: true
            )
        }

        success {
            echo 'Enterprise API automation execution passed.'
        }

        unstable {
            echo 'Execution completed with test failures.'
        }

        failure {
            echo 'Enterprise API automation execution failed.'
        }

        cleanup {
            deleteDir()
        }
    }
}
