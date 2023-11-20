pipeline {
    agent any
    environment {
        // Define la variable de entorno PATH para incluir la ubicación de Angular CLI.
        PATH = "/ruta/a/angular/cli:$PATH"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Install Dependencies') {
            steps {
                // Instala Node.js y las dependencias del proyecto (asegúrate de que Node.js y npm estén instalados)
                sh 'curl -sL https://deb.nodesource.com/setup_14.x | bash -'
                sh 'apt-get install -y nodejs'
                sh 'npm install'
            }
        }
        stage('Build') {
            steps {
                // Compilar y construir la aplicación Angular
                sh 'npm run build'  // O "ng build" si tienes Angular CLI globalmente instalado
            }
        }
        // Agrega más etapas aquí según tus necesidades (por ejemplo, etapas de pruebas o despliegue).
    }
    post {
        success {
            // Puedes agregar acciones que se ejecuten cuando el Pipeline tenga éxito.
        }
        failure {
            // Puedes agregar acciones que se ejecuten cuando el Pipeline falle.
        }
    }
}
