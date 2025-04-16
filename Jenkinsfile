pipeline {
	agent any
    tools {
        maven 'Maven'
    }

	environment {
	DOCKERHUB_CREDENTIALS = credentials('09fd92cd-bf60-4748-81b6-27076c2a5f5d')
	BUILD_TIMESTAMP = "${new Date().format("yyyyMMdd-HHmmss")}"
	}
	
	stages {
		stage("Building the Student microservice") {
			steps{
				script{
                    sh 'rm -rf ./*.jar'
                    sh 'mvn clean install -DskipTests'
					sh 'sudo docker build -t vrishin/student-survey-api:$BUILD_TIMESTAMP .'
				}
			}	
		}
		
		stage("Login to Docker Hub"){
			steps{
				script{
					sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'

				}
			}
		}
		stage("Pushing Image to DockerHub") {
			steps {
				script {
					sh 'sudo docker push vrishin/student-survey-api:$BUILD_TIMESTAMP'
					}
				}
			}
			
			
		stage("Deploying to Rancher") {
			steps {
				sh 'kubectl set image deployment/reddys-microservice container-1=vrishin/student-survey-api:$BUILD_TIMESTAMP -n default'
				sh 'kubectl rollout status deployment/reddys-microservice -n default'
			}
		}
		
		
	}
	
}