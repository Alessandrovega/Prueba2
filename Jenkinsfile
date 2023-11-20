pipeline {
    agent any

    environment {
        // Establece el JDK que utilizará Jenkins
        JDK_HOME = tool 'JDK11'
        // Especifica la ubicación de Maven en Jenkins
        MAVEN_HOME = tool 'Maven'
        // Ajusta la ruta según la estructura de tu proyecto
        APP_HOME = "${WORKSPACE}/ruta/a/tu/proyecto"
    }

    stages {
        stage('Checkout') {
            steps {
                // Paso para clonar el repositorio (asegúrate de configurar las credenciales de Git en Jenkins)
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Paso para compilar y empaquetar el proyecto con Maven
                sh "${MAVEN_HOME}/bin/mvn -f ${APP_HOME}/pom.xml clean install"
            }
        }

        stage('Test') {
            steps {
                // Puedes agregar pasos para ejecutar pruebas si tienes configuradas pruebas unitarias
                // sh "${MAVEN_HOME}/bin/mvn -f ${APP_HOME}/pom.xml test"
            }
        }
    }

    post {
        success {
            // Puedes agregar pasos adicionales en caso de éxito
            echo 'Build successful!'
        }

        failure {
            // Puedes agregar pasos adicionales en caso de fallo
            echo 'Build failed!'
        }
    }
}
