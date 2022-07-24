import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGenerator {
    
    public void create(InputStream inputStream, String fileName, String text) throws Exception {

        // ler imagem
        //InputStream inputStream = new FileInputStream(new File("images/movie1.jpg"));
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_10.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // criar nova imagem em memória, com transaparência e novo tamanho
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original par nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        
        // Adaptar tamanho e posição do texto
        int fontSize = 32;
        int textInitialPosition = 100;

        // configurar a fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setFont(font);

        // escrever um texto        
        graphics.setColor(Color.ORANGE);
        graphics.drawString(text, textInitialPosition, newHeight-100);

        // gravar a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("images/"+fileName));

        System.out.println("Tamanho imagem " + width);
        System.out.println("Posição Texto " + textInitialPosition);
        System.out.println("Fonte " + fontSize);
    }
}
