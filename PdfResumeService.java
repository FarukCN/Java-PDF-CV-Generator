import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class PdfResumeService {

    // Türkçe karakterleri Helvetica fontunun desteklediği karakterlere çevirir
    private String cleanText(String text) {
        if (text == null) return "";
        return text.replace("ı", "i")
                   .replace("İ", "I")
                   .replace("ğ", "g")
                   .replace("Ğ", "G")
                   .replace("ü", "u")
                   .replace("Ü", "U")
                   .replace("ş", "s")
                   .replace("Ş", "S")
                   .replace("ö", "o")
                   .replace("Ö", "O")
                   .replace("ç", "c")
                   .replace("Ç", "C");
    }

    public void generatePdf(ResumeProfile profile, String outputPath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        try (PDPageContentStream content = new PDPageContentStream(document, page)) {
            // 1. Profil Fotoğrafı
            File imgFile = new File(profile.getPhotoPath());
            if (imgFile.exists()) {
                PDImageXObject pdImage = PDImageXObject.createFromFile(profile.getPhotoPath(), document);
                content.drawImage(pdImage, 450, 680, 100, 120);
            }

            // 2. Ad Soyad
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 22);
            content.newLineAtOffset(50, 780);
            content.showText(cleanText(profile.getFullName()));
            content.endText();

            // 3. Unvan
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 13);
            content.newLineAtOffset(50, 758);
            content.showText(cleanText(profile.getTitle()));
            content.endText();

            // 4. İletişim Bilgileri
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(50, 735);
            content.showText(cleanText("E-posta: " + profile.getEmail() + " | Tel: " + profile.getPhone()));
            content.newLineAtOffset(0, -14);
            content.showText(cleanText("Konum: " + profile.getAddress()));
            content.endText();

            // Çizgi
            content.moveTo(50, 665);
            content.lineTo(550, 665);
            content.stroke();

            // 5. Özet Başlığı ve Metni
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 13);
            content.newLineAtOffset(50, 640);
            content.showText("OZGECMIS OZETI");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(50, 620);
            content.showText(cleanText(profile.getSummary()));
            content.endText();

            // 6. Deneyimler Başlığı
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 13);
            content.newLineAtOffset(50, 580);
            content.showText("IS DENEYIMLERI");
            content.endText();

            // 7. İş Deneyimleri Döngüsü
            float currentY = 555;
            for (Experience exp : profile.getExperiences()) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 11);
                content.newLineAtOffset(50, currentY);
                content.showText(cleanText(exp.getRole() + " - " + exp.getCompany()));
                content.endText();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA_OBLIQUE, 9);
                content.newLineAtOffset(420, currentY);
                content.showText(cleanText(exp.getDuration()));
                content.endText();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(50, currentY - 16);
                content.showText(cleanText(exp.getDescription()));
                content.endText();

                currentY -= 55;
            }
        }

        document.save(outputPath);
        document.close();
        System.out.println("PDF basariyla uretildi: " + outputPath);
    }
}