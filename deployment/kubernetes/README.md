# GitLab DevSecOps CI/CD Pipeline

## 1. Project Overview

Project ini merupakan implementasi otomatisasi CI/CD Pipeline dengan pendekatan DevSecOps menggunakan GitLab CI/CD. Tujuan project ini adalah mengintegrasikan proses build, testing, security scanning, containerization, dan deployment aplikasi ke dalam satu alur kerja yang terotomatisasi.

Aplikasi yang digunakan adalah GitLab DevSecOps Demo App yang dikembangkan menggunakan Spring Boot dan Java 21. GitLab digunakan sebagai platform pengelolaan source code dan pipeline, sedangkan GitLab Runner bertugas menjalankan setiap proses yang telah dikonfigurasi.

Docker digunakan untuk mengemas aplikasi ke dalam container, sementara Docker Hub berfungsi sebagai tempat penyimpanan Docker image. Pemeriksaan keamanan dilakukan menggunakan Trivy untuk membantu mengidentifikasi potensi kerentanan pada source code dan Docker image.

## 2. Project Objectives

Project ini bertujuan untuk membangun pipeline CI/CD yang dapat mengotomatisasi proses pengembangan hingga deployment aplikasi. Implementasi ini juga bertujuan menerapkan prinsip DevSecOps dengan mengintegrasikan pemeriksaan keamanan ke dalam tahapan pipeline.

Selain itu, project ini memberikan pengalaman dalam mengonfigurasi GitLab Runner, mengelola kredensial menggunakan CI/CD Variables, membangun Docker image, serta melakukan deployment dan verifikasi aplikasi melalui health check.

## 3. Technology Stack

Teknologi dan tools yang digunakan dalam project ini meliputi:

- **GitLab:** Platform untuk mengelola repository dan menjalankan CI/CD Pipeline.
- **GitLab Runner:** Menjalankan job pipeline menggunakan Shell Executor.
- **Java 21:** Bahasa pemrograman dan runtime aplikasi.
- **Spring Boot:** Framework untuk membangun aplikasi.
- **Maven:** Mengelola dependency dan proses build aplikasi.
- **Trivy:** Melakukan pemeriksaan keamanan terhadap source code dan Docker image.
- **Docker:** Mengemas dan menjalankan aplikasi dalam container.
- **Docker Hub:** Menyimpan dan mendistribusikan Docker image.
- **Git:** Mengelola version control source code.

## 4. Project Architecture

Arsitektur project menggunakan GitLab sebagai pusat pengelolaan source code dan otomatisasi pipeline. GitLab Runner dihubungkan dengan project untuk menjalankan setiap job sesuai konfigurasi pada file `.gitlab-ci.yml`.

Alur proses dimulai ketika perubahan source code tersedia pada repository GitLab. Selanjutnya, pipeline menjalankan proses build dan testing aplikasi, dilanjutkan dengan security scanning menggunakan Trivy. Setelah tahapan tersebut, aplikasi dikemas menjadi Docker image dan dikirim ke Docker Hub. Tahap deployment kemudian dijalankan untuk memperbarui container aplikasi dan melakukan pemeriksaan kesehatan aplikasi.

**Alur CI/CD Pipeline:**

`GitLab Repository → Build → Test → Security Scan → Docker Build → Docker Hub → Deploy → Health Check`

## 5. Project Implementation

### 5.1 GitLab Repository Setup

Tahap pertama dilakukan dengan membuat repository GitLab bernama `gitlab-devsecops-cicd-pipeline` sebagai tempat pengelolaan source code aplikasi dan konfigurasi pipeline.

Repository ini memiliki folder `application/demo-app` yang berisi source code aplikasi, folder `deployment/kubernetes` yang menyimpan konfigurasi deployment Kubernetes, serta file `.gitlab-ci.yml` yang digunakan untuk mendefinisikan tahapan CI/CD Pipeline.

Pemisahan direktori tersebut membantu mengorganisasi source code aplikasi dan konfigurasi deployment agar lebih mudah dikelola.

**Screenshot:**
![alt text](<GitLab Repository.png>)

*Gambar 1. Halaman repository GitLab yang menampilkan struktur project, source code aplikasi, konfigurasi deployment, dan file CI/CD Pipeline.*

### 5.2 GitLab Runner Configuration

GitLab Runner digunakan sebagai komponen yang bertugas menjalankan job dari GitLab CI/CD Pipeline. Pada project ini, GitLab Runner didaftarkan menggunakan Shell Executor agar perintah yang terdapat dalam pipeline dapat dijalankan langsung pada lingkungan GitLab Playground.

Runner didaftarkan dengan nama `gitlab-host-runner` dan menggunakan tag `devsecops`, `docker`, serta `linux`. Tag tersebut digunakan untuk menghubungkan job pipeline dengan runner yang sesuai.

Setelah proses registrasi berhasil diverifikasi, konfigurasi runner disimpan pada file `/etc/gitlab-runner/config.toml` dan service GitLab Runner dijalankan.

**Screenshot:**
![alt text](gitlabr-runner.png)

*Gambar 2. Proses registrasi GitLab Runner menggunakan Shell Executor serta verifikasi koneksi antara GitLab Runner dan GitLab Server.*

### 5.3 GitLab CI/CD Variables Configuration

Untuk menjaga keamanan informasi autentikasi, project ini menggunakan fitur GitLab CI/CD Variables. Fitur ini memungkinkan kredensial disimpan sebagai variabel terpisah sehingga tidak perlu dituliskan secara langsung di dalam source code maupun file konfigurasi pipeline.

Terdapat dua variabel yang digunakan untuk menghubungkan GitLab dengan Docker Hub, yaitu `DOCKERHUB_USERNAME` untuk menyimpan username Docker Hub dan `DOCKERHUB_TOKEN` untuk menyimpan access token autentikasi.

Kedua variabel tersebut dikonfigurasi melalui pengaturan CI/CD Variables pada project GitLab dengan nilai yang disembunyikan.

**Screenshot:**

![alt text](<../../GitLab CI/CD Variables.png>)

*Gambar 3. Konfigurasi GitLab CI/CD Variables untuk menyimpan kredensial Docker Hub secara terpisah dari source code.*

### 5.4 CI/CD Pipeline Configuration

Konfigurasi otomatisasi dibuat melalui file `.gitlab-ci.yml`. File ini digunakan untuk menentukan tahapan pipeline, perintah yang dijalankan pada setiap job, serta hubungan antara proses build hingga deployment aplikasi.

Project ini menggunakan lima tahapan utama, yaitu Build, Test, Security, Docker Build, dan Deploy.

Tahap Build digunakan untuk mengompilasi aplikasi menggunakan Maven. Tahap Test menjalankan pengujian aplikasi untuk memeriksa fungsionalitasnya. Tahap Security menjalankan pemeriksaan menggunakan Trivy terhadap file project.

Selanjutnya, tahap Docker Build digunakan untuk membuat Docker image berdasarkan Dockerfile. Tahap Deploy digunakan untuk menjalankan proses deployment aplikasi dan dikonfigurasi agar dapat dijalankan secara manual.

**Screenshot:**
![alt text](<../../pipeline stages.png>)

*Gambar 4. Visualisasi lima tahapan GitLab CI/CD Pipeline yang terdiri dari Build, Test, Security, Docker Build, dan Deploy.*

### 5.5 Pipeline Execution

Setelah konfigurasi pipeline selesai, proses eksekusi dilakukan melalui GitLab CI/CD. Setiap job dijalankan oleh GitLab Runner berdasarkan tahapan yang telah ditentukan dalam file `.gitlab-ci.yml`.

Hasil eksekusi dapat dipantau melalui halaman Pipelines pada GitLab. Pada hasil implementasi yang didokumentasikan, pipeline berhasil diselesaikan dengan status **Passed**.

Keberhasilan tersebut menunjukkan bahwa seluruh job pada eksekusi pipeline yang ditampilkan berhasil dijalankan.

**Screenshot:

![alt text](<../../Pipeline Passed.png>)

*Gambar 5. Hasil eksekusi GitLab CI/CD Pipeline dengan status Passed yang menunjukkan tahapan pipeline berhasil diselesaikan.*

### 5.6 Docker Image Build and Registry

Setelah proses build dan pemeriksaan keamanan, aplikasi dikemas menggunakan Dockerfile menjadi Docker image. Image tersebut kemudian dikirim ke Docker Hub menggunakan kredensial yang telah disimpan pada GitLab CI/CD Variables.

Repository Docker Hub yang digunakan adalah `richardakbar/gitlab-devsecops-demo-app`. Image aplikasi dipublikasikan menggunakan tag `latest` dan tag berdasarkan commit SHA.

Penggunaan tag commit SHA membantu mengidentifikasi versi image berdasarkan commit source code yang digunakan. Dengan demikian, image dapat ditelusuri berdasarkan versi perubahan aplikasi.

**Screenshot:**
![alt text](../../dockerhub.png)

*Gambar 6. Repository Docker Hub yang menampilkan Docker image aplikasi dengan tag latest dan commit SHA 167edf27.*

### 5.7 Application Deployment and Health Check

Proses deployment dilakukan melalui job `deploy` pada GitLab CI/CD Pipeline. Job ini dikonfigurasi menggunakan mekanisme manual sehingga deployment dapat dijalankan setelah tahapan sebelumnya berhasil diselesaikan.

Pada proses deployment, Docker melakukan pull terhadap image aplikasi berdasarkan commit SHA. Selanjutnya, container aplikasi sebelumnya diperbarui dengan menjalankan container baru menggunakan image tersebut.

Aplikasi dijalankan dengan pemetaan port `8083:8081`, sehingga aplikasi dapat diakses melalui port 8083 pada host.

Setelah container dijalankan, pipeline melakukan health check melalui endpoint `/health`. Pemeriksaan dilakukan secara berkala untuk memastikan aplikasi telah siap berjalan.

Berdasarkan log deployment, aplikasi memberikan respons `UP` dengan informasi `GitLab DevSecOps Demo App`. Proses deployment kemudian dinyatakan berhasil dan job berakhir dengan status `Job succeeded`.

**Screenshot:**
![alt text](../../jobdedploy.png)


*Gambar 7. Log deployment yang menunjukkan proses pull Docker image, pembaruan container, serta health check dengan respons aplikasi berstatus UP.*

### 5.8 Application Monitoring Dashboard

Setelah deployment berhasil, aplikasi dapat diakses melalui browser untuk melihat tampilan dashboard monitoring.

Dashboard menampilkan informasi mengenai status aplikasi, health status, versi aplikasi, framework, runtime, dan teknologi containerization yang digunakan.

Berdasarkan tampilan dashboard, aplikasi menunjukkan status `Running` dan health status `UP`. Informasi tersebut menjadi bukti bahwa aplikasi berhasil dijalankan dan dapat diakses melalui browser setelah proses deployment.

**Screenshot:**
![alt text](../../dashboard.png)

*Gambar 8. Tampilan Application Monitoring Dashboard yang menunjukkan aplikasi berjalan dengan status Running dan Health Status UP.*

## 6. Project Results

Berdasarkan implementasi yang telah dilakukan, project ini berhasil membangun alur otomatisasi CI/CD menggunakan GitLab dan GitLab Runner.

Pipeline berhasil menjalankan tahapan Build, Test, Security, Docker Build, dan Deploy. Docker image aplikasi juga berhasil dipublikasikan ke Docker Hub dengan tag berbasis commit SHA dan `latest`.

Proses deployment berhasil memperbarui container aplikasi dan melakukan health check melalui endpoint `/health`. Hasil pemeriksaan menunjukkan bahwa aplikasi memberikan respons `UP` dan dapat diakses melalui dashboard monitoring.

Implementasi ini menunjukkan penerapan integrasi CI/CD dan praktik DevSecOps dalam proses build, testing, pemeriksaan keamanan, containerization, serta deployment aplikasi.

## 7. Conclusion

Project GitLab DevSecOps CI/CD Pipeline berhasil mengimplementasikan otomatisasi proses pengembangan dan deployment aplikasi menggunakan GitLab CI/CD, GitLab Runner, Trivy, Docker, dan Docker Hub.

Melalui project ini, proses build, testing, security scanning, pembuatan Docker image, dan deployment dapat dikelola melalui satu pipeline terintegrasi. Penggunaan GitLab CI/CD Variables juga membantu memisahkan kredensial autentikasi dari source code.

Project ini memberikan pengalaman praktis dalam membangun workflow DevSecOps serta memahami proses otomatisasi deployment aplikasi berbasis container.

## 8. Repository Links

- **GitHub Repository:** https://github.com/RichardAkbar/gitlab-devsecops-cicd-pipeline
- **GitLab Project:** http://gitlab.kodekloud.com/root/gitlab-devsecops-cicd-pipeline
- **Docker Hub Repository:** https://hub.docker.com/repository/docker/richardakbar/gitlab-devsecops-demo-app
