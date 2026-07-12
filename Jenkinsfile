pipeline {

    agent any

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
            choices: [
                'dev',
                'qa',
                'stage'
            ],
            description: 'Target test environment'
        )
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(
            logRotator(
                numToKeepStr: '20'
            )
        )
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
                    java -version
                    mvn -version
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
                    )
                ]) {

                    sh '''
                        set +x

                        echo "Environment: $ENVIRONMENT"
                        echo "Suite: $TEST_SUITE"

                        case "$TEST_SUITE" in

                            smoke)
                                mvn clean test \
                                  -Denv="$ENVIRONMENT" \
                                  -Dsurefire.suiteXmlFiles=smoke.xml
                                ;;

                            regression)
                                mvn clean test \
                                  -Denv="$ENVIRONMENT" \
                                  -Dsurefire.suiteXmlFiles=regression.xml
                                ;;

                            unit)
                                mvn clean test \
                                  -Denv="$ENVIRONMENT" \
                                  -Dsurefire.suiteXmlFiles=unit.xml
                                ;;

                            integration)
                                mvn clean test \
                                  -Denv="$ENVIRONMENT" \
                                  -Dsurefire.suiteXmlFiles=integration.xml
                                ;;

                            full)
                                mvn clean test \
                                  -Denv="$ENVIRONMENT"
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

            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            allure(
                commandline: 'Allure',
                includeProperties: false,
                results: [
                    [
                        path: 'target/allure-results'
                    ]
                ]
            )

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