pipeline {
    agent any
    tools {
        maven 'Maven'
    }

    environment {
        REGISTRY_CREDENTIALS = credentials('09fd92cd-bf60-4748-81b6-27076c2a5f5d')
        TIMESTAMP_VERSION = "${new Date().format("yyyyMMdd-HHmmss")}"
    }
    
    stages {
        stage("Compile Survey Service") {
            steps {
                script {
                    sh 'rm -rf ./*.jar'
                    sh 'mvn clean install -DskipTests'
                    sh 'sudo docker build -t vrishin/student-survey-api-micro:$TIMESTAMP_VERSION .'
                }
            }    
        }
        
        stage("Authenticate with Container Registry") {
            steps {
                script {
                    sh 'echo $REGISTRY_CREDENTIALS_PSW | sudo docker login -u $REGISTRY_CREDENTIALS_USR --password-stdin'
                }
            }
        }
        
        stage("Publish Container Image") {
            steps {
                script {
                    sh 'sudo docker push vrishin/student-survey-api-micro:$TIMESTAMP_VERSION'
                }
            }
        }
            
        stage("Apply Kubernetes Updates") {
            steps {
                sh 'kubectl set image deployment/swe-micros container-1=vrishin/student-survey-api-micro:$TIMESTAMP_VERSION -n default'
                sh 'kubectl rollout status deployment/swe-micros -n default'
            }
        }
    }
}