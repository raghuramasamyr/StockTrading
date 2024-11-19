pipeline {
    agent any

    environment {
        PROJECT_ID = 'my-spring-app'
        IMAGE_NAME = 'gcr.io/$PROJECT_ID/spring-boot-app'
        CLOUD_RUN_SERVICE = 'spring-boot-app'
        REGION = 'us-central1'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                withCredentials([file(credentialsId: 'stock-trading', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    bat 'gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS'
                    bat 'gcloud config set project $PROJECT_ID'
                    bat 'gcloud builds submit --tag $IMAGE_NAME'
                }
            }
        }
        stage('Deploy to Cloud Run') {
            steps {
                withCredentials([file(credentialsId: 'stock-trading', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    bat 'gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS'
                    bat 'gcloud config set project $PROJECT_ID'
                    bat 'gcloud run deploy $CLOUD_RUN_SERVICE --image $IMAGE_NAME --platform managed --region $REGION --allow-unauthenticated'
                }
            }
        }
    }
    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
