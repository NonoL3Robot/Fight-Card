pipeline {
    agent any

    tools {
        maven "Maven 3.9.6"
    }

    stages {
        stage('Hello') {
            steps {
                script {
                    echo 'Hello World !'
                }
            }
        }

        stage('Maven Build') {
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Build Docker image') {
            steps {
                script {
                    dockerImage = docker.build('fightcard:latest')
                    bat "docker image prune -f"
                }
            }
        }

        stage('Start Docker Container') {
            steps {
                script {
                    try {
                        bat "docker stop fightcard"
                        bat "docker rm fightcard"
                    } catch (Exception e) {
                        echo '404 Not Found : fightcard'
                    }
                    bat "docker run --name fightcard -d -p 9075:8080 fightcard:latest FightCard.jar"
                }
            }
        }
    }
}