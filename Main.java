



public class Main {
    public static void main(String[] args) {
        try {
            // Profil nesnesi oluşturuluyor
            ResumeProfile profile = new ResumeProfile(
                "Faruk Can Ünal",
                "Senior Software Engineer",
                "fcanunal@gmail.com",
                "+90 542 351 62 20",
                "Istanbul, Turkiye",
                "Yazilim mimarisi ve mikroservis sistemleri uzerine calisan yazılım mühendisi.",
                "profile.jpeg"
            );

            // 3 Hayali Deneyim
            profile.addExperience(new Experience(
                "NovaTech Solutions",
                "Lead Backend Developer",
                "2024 - Guncel",
                "Yuksek trafikli odeme sistemlerinin mikroservis donusumunu yonetti."
            ));

            profile.addExperience(new Experience(
                "Aether Game Studio",
                "Systems Software Engineer",
                "2022 - 2024",
                "Gercek zamanli ag soket mimarisi ve veri serilestirme katmanini gelistirdi."
            ));

            profile.addExperience(new Experience(
                "KodLab R&D",
                "Junior Software Developer",
                "2021 - 2022",
                "Java Spring Boot tabanli RESTful API servislerinin bakimini yapti."
            ));

            // Servis sınıfı çağrılıyor
            PdfResumeService pdfService = new PdfResumeService();
            pdfService.generatePdf(profile, "ozgecmis_ciktisi.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}