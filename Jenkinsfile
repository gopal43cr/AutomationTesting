pipeline {
    agent any
    triggers {
        cron('37 15 * * *')   
    }
    tools {
        maven 'Maven' 
    }

    stages {
        stage('Clean and Test') {
            steps {
                echo 'Building and Testing...'
                bat 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}