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
                  echo 'Build Docker Image ## 1'
                    bat ''' gcloud auth activate-service-account --key-file=%GOOGLE_APPLICATION_CREDENTIALS%
                     gcloud config set project %PROJECT_ID%
                     gcloud builds submit --tag %IMAGE_NAME% '''
                     echo 'Build Docker Image ## 2'
                }
            }
        }
        stage('Deploy to Cloud Run') {
            steps {
                withCredentials([file(credentialsId: 'stock-trading', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    echo 'BDeploy to Cloud Run ## 1'
                    bat ''' gcloud auth activate-service-account --key-file=%GOOGLE_APPLICATION_CREDENTIALS%
                     gcloud config set project %PROJECT_ID%
                     gcloud run deploy %CLOUD_RUN_SERVICE% --image %IMAGE_NAME% --platform managed --region %REGION% --allow-unauthenticated '''
                echo 'BDeploy to Cloud Run ## 1'
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
