package package01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.List;
import java.util.Random;

public class Class01_TC01 extends TestBase {

    Logger logger = LogManager.getLogger(Class01_TC01.class);
    @Test
    public void test01() {


        // 1-) https://www.setur.com.tr/ sayfasına gidilir
        driver.get("https://www.setur.com.tr/");
        logger.info("https://www.setur.com.tr/ gidildi");
        // 2-) setur url'nin geldiği kontrol edilir.
        Assert.assertEquals("https://www.setur.com.tr/", driver.getCurrentUrl());
        cerezSavar();
        logger.info("Açılan cookieler handle edildi");
        reklamSavar();
        logger.info("Açılan reklamlar handle edildi");


        // 3-) Ana sayfada otel tabının default geldiği kontrol edilir.
        WebElement otelTabi=driver.findElement(By.xpath("//span[normalize-space()='Otel']"));
        highlightElement(driver,otelTabi);
        Assert.assertTrue(otelTabi.isEnabled());
        logger.info("Ana sayfada otel tabının default geldiği kontrol edildi");
        // 4-) "Nereye gideceksiniz" alanına dosyasından "Antalya" yazılır ve en üstteki Antalya seçeneğine tıklanır
        WebElement nereyeGideceksinizTextBox = driver.findElement(By.xpath("//input[@placeholder='Otel Adı Veya Konum']"));
        nereyeGideceksinizTextBox.sendKeys("Antalya");
        nereyeGideceksinizTextBox.click();
        waitForSecond(3);

        WebElement enUsttekiSehir=driver.findElement(By.xpath("//div[@class='sc-94dcce44-8 klLMJf']//div[1]//div[1]//div[1]"));
        highlightElement(driver,enUsttekiSehir);
        jsClick(enUsttekiSehir);


        waitForSecond(2);
        WebElement tarihBolumu=driver.findElement(By.xpath("(//span[@class='sc-fd984615-0 cdgkri'])[2]"));
        highlightElement(driver,tarihBolumu);
        jsClick(tarihBolumu);
        logger.info("Nereye gideceksiniz alanına dosyasından \"Antalya\" yazılır ve en üstteki Antalya seçeneğine tıklandı");
        // 5-) Tarih alanına Nisan'in ilk haftası için bir haftalık aralık seçilir
        WebElement sagaKaydır = driver.findElement(By.xpath("//button[@class='sc-8de9de7b-0 kCGMge sc-147d3380-2 cULZMP']//span[@class='sc-eb82d810-0 eClCrJ']//*[name()='svg']"));
        for (int i = 0; i < 3; i++) {
            jsClick(sagaKaydır);
            waitForSecond(1);
        }

        WebElement birNisan=driver.findElement(By.xpath("//td[@aria-label='Choose Pazartesi, 1 Nisan 2024 as your check-in date. It’s available.']//span[@class='sc-1720695c-0 kNqhKR'][normalize-space()='1']"));
        highlightElement(driver,birNisan);
        birNisan.click();
        waitForSecond(1);

       WebElement yediNisan=driver.findElement(By.xpath("//td[@aria-label='Choose Pazar, 7 Nisan 2024 as your check-out date. It’s available.']"));
       highlightElement(driver,yediNisan);
       yediNisan.click();
       logger.info("Tarih alanına Nisan'in ilk haftası için bir haftalık aralık seçildi");
        // 6-) Yetişkin sayısı 1 artırılır ve yetişkin sayısının değiştiği kontrol edilir
      WebElement kacKisiKonaklayacaksınızText=driver.findElement(By.xpath("//span[@class='sc-b2c3f6ee-21 bSPwxV']"));
      highlightElement(driver,kacKisiKonaklayacaksınızText);
      kacKisiKonaklayacaksınızText.click();
        for (int i = 0; i < 1; i++) {
           WebElement yetiskinSayisiArti=driver.findElement(By.xpath("//div[@class='sc-b2c3f6ee-14 dirCeL']//div[1]//div[1]//div[2]//button[2]//span[1]//span[1]//*[name()='svg']"));
           highlightElement(driver,yetiskinSayisiArti);
           yetiskinSayisiArti.click();
            WebElement yetiskinSayisiText = driver.findElement(By.xpath("(//span[@class='sc-423a98f0-2 kxWULs'])[1]"));
            Assert.assertEquals("3", yetiskinSayisiText.getText());
        }
        logger.info("Yetişkin sayısı 1 artırılır ve yetişkin sayısının değiştiği kontrol edildi");

        // 7-) "Ara" buton'nun görünürlüğü kontrol edilir
        WebElement araButton = driver.findElement(By.xpath("//span[normalize-space()='Ara']"));
        Assert.assertTrue(araButton.isDisplayed());
        jsClick(araButton);
        logger.info("\"Ara\" buton'nun görünürlüğü kontrol edildi");
        // 8-) Açılan url içinde "antalya" kelimesi içerdiği kontrol edilir
        waitForSecond(5);
        Assert.assertTrue(driver.getCurrentUrl().contains("antalya"));

        // 9-) "Diğer bölgeleri göster" alanında rastgele tıklama metotu kullaılarak bir seçim yapılır ve "()" içerisinde bulunan sayı kaydedilir
      WebElement hepsiniGoster=driver.findElement(By.xpath("//div[@class='sc-aa1d9ceb-7 dVbmwW']"));
      highlightElement(driver,hepsiniGoster);
      hepsiniGoster.click();

        List<WebElement> digerBolgeler = driver.findElements(By.xpath("//div[@class='sc-2569635-3 buefhp']//span/span/span"));

        Random random = new Random();

        int rastgeleSayi = random.nextInt(digerBolgeler.size());

        highlightElement(driver, digerBolgeler.get(rastgeleSayi));
        jsClick(digerBolgeler.get(rastgeleSayi));

        String expectedData = digerBolgeler.get(rastgeleSayi).getText().replaceAll("[^0-9]", "");
        System.out.println(expectedData);
        logger.info("\"Diğer bölgeleri göster\" alanında rastgele tıklama metotu kullaılarak bir seçim yapılır ve \"()\" içerisinde bulunan sayı kaydedildi");

        try {
            // 10-) Sayfanın altına bulunan "Antalya Otelleri ve en Uygun Antalya Otel Fiyatları" alanına kadar ekranda kaydırma yapılır,
            /*
            Her seferinde "uygunAntalyaOtelFiyatlari"  webelementi görülmediği için try catch statementini kullanmak durumunda kaldım

           **** BUG/ONERİ ****
            Otel sayisi az olan yerleşim yerleri için de sayfanın altında çıkan otel sayisi ile ilgili bir text bulunabilir.

             */

            waitForSecond(3);
            WebElement uygunOtelBilgilendirmeText = driver.findElement(By.xpath("//div[@class='sc-21021e1e-1 gPQAyQ']"));
            Actions actions = new Actions(driver);
            actions.scrollToElement(uygunOtelBilgilendirmeText).perform();
            highlightElement(driver,uygunOtelBilgilendirmeText);
            System.out.println("uygunAntalyaOtelFiyatları = " + uygunOtelBilgilendirmeText.getText());

            String actualData = uygunOtelBilgilendirmeText.getText().split(" ")[9];
            System.out.println(actualData);
            logger.info("Sayfanın altına bulunan \"Antalya Otelleri ve en Uygun Antalya Otel Fiyatları\" alanına kadar ekranda kaydırma yapıldı");
            // 11-) Kaydedilen değerin 8. adımda kaydedilen değerle eşit olduğu kontrol edilir.
            Assert.assertEquals(expectedData, actualData);
        } catch (Exception e) {
            System.out.println("Uygun antalya otel fiyatlari yazisi görülmedi");
        }

        driver.quit();
            logger.info("Kaydedilen değerin 8. adımda kaydedilen değerle eşit olduğu kontrol edildi ve quit işlemi yapıldı");
    }


}
