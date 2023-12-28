package package01;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class JavaSoruKaynakOdev extends TestBase {




    public void rastgeleAyakkabıSec() {

        List<WebElement> butunAyakkkabılar = driver.findElements(By.xpath("//img[@data-image-index]"));
        Random random = new Random();
        int randomIndex = random.nextInt(butunAyakkkabılar.size());//0-48
        waitForSecond(1);
        if (randomIndex != 0) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            butunAyakkkabılar.get(randomIndex).click();
        }
    }

    @Test
    public void test01() {

        // https://www.amazon.com.tr/ bağlantısını açın
        driver.get("https://www.amazon.com.tr/");
        driver.navigate().refresh();
        waitForSecond(1);
        //tümü menüsünü açın
        driver.findElement(By.xpath("//span[@class='hm-icon-label']")).click();
        waitForSecond(1);
        //Ayakkabı seçeneğini seçin.
        driver.findElement(By.xpath("(//a[@class='hmenu-item'])[4]")).click();
        waitForSecond(1);
        //'Çizme ve Bot' alt menüsüne tıklayın.
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).perform();

        waitForSecond(1);
        //Random belirlediğiniz bir çizme veya botu seçiniz.
        driver.findElement(By.xpath("//input[@id='sp-cc-accept']")).click();

        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        //ürünün ayak numarasını belirleyin.
        driver.findElement(By.xpath("//button[@aria-label='40']")).click();//numara seç

        rastgeleAyakkabıSec();


        //ürünün stokta olup olmadığını kontrol edin.

        //stokta varsa ürünü sepete ekleyiniz.
        //Stokta yoksa başka ayak numarası seçiniz.
        //Sepete gidiniz.

        WebElement stoktaVarYazısı = driver.findElement(By.xpath("(//div[@class='a-box-inner'])[1]"));
        if (stoktaVarYazısı.getText().contains("var") || stoktaVarYazısı.getText().contains("kaldı.")) {

            WebElement sepeteEkle = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
            bekle(sepeteEkle);
            String bilgiListesi = driver.findElement(By.xpath("(//div[@class='a-box-inner'])[1]")).getText();
            sepeteEkle.click();


            String urunRengi = driver.findElement(By.xpath("(//span[@class='a-size-base'])[2]")).getText();
            String urunFiyatı = driver.findElement(By.xpath("//span[@class='a-price sw-subtotal-amount']")).getText();
            String urunNo = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();


            // Assert.assertTrue(genelUrunBilgisi.contains(urunRengi));

            Assert.assertTrue(bilgiListesi.contains(urunFiyatı));
            waitForSecond(1);
            // Assert.assertTrue(genelUrunBilgisi.contains(urunNo));



        } else
            while (true) {

                //Eğer stokta var yazısı yoksa sayfa geri gelmeli ve baştan seçim yapılmalı
                driver.navigate().refresh();
                driver.navigate().back();
                rastgeleAyakkabıSec();

                WebElement sepeteEkle = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
                String bilgiListesi = driver.findElement(By.xpath("(//div[@class='a-box-inner'])[1]")).getText();
                sepeteEkle.click();


                String urunRengi = driver.findElement(By.xpath("(//span[@class='a-size-base'])[2]")).getText();
                String urunFiyatı = driver.findElement(By.xpath("//span[@class='a-price sw-subtotal-amount']")).getText();
                String urunNo = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();


                // Assert.assertTrue(genelUrunBilgisi.contains(urunRengi));

                Assert.assertTrue(bilgiListesi.contains(urunFiyatı));
                waitForSecond(1);
                //  Assert.assertTrue(genelUrunBilgisi.contains(urunNo));


                break;
            }


        //Ürünün fiyat, renk ve numarasının aynı olduğunu doğrulayınız.

        // String genelUrunBilgisi=driver.findElement(By.xpath("//div[@id='dp']")).getText();
//
        // String  urunRengi=driver.findElement(By.xpath("(//span[@class='a-size-base'])[2]")).getText();
        // String  urunFiyatı=driver.findElement(By.xpath("(//span[@aria-hidden='true'])[7]")).getText();
        // String  urunNo=driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();
//
//
        // Assert.assertTrue(genelUrunBilgisi.contains(urunRengi));
        // Assert.assertTrue(genelUrunBilgisi.contains(urunFiyatı));
        // Assert.assertTrue(genelUrunBilgisi.contains(urunNo));
    }
}

