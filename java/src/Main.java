package mainPackage;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alexandre on 12/03/2016.
 */
public class Main {

    public Steganographie steghide = new Steganographie();
    private String p;

    public static void main(String[] args) {

        Main main = new Main();
        main.p = main.steghide.p;
        MainInterface mi = new MainInterface();

    }

    /**
     * Send simple msg
     * @param msg
     */
    public void send(String msg) {
        textToImage(msg);
        File dest = new File("resources"+p+"cover.bmp");
        /*
            send cover.bmp TO destinataire
         */
        dest.delete();
    }

    /**
     * Send an steganed msg
     * @param msg
     * @param coverMsg
     * @param destinataire
     */
    public void send(String msg, String coverMsg, String destinataire) {
        textToImage(coverMsg);
        steghide.insert(msg, destinataire);
        File dest = new File("resources"+p+"cover.bmp");
        /*
            send cover.bmp TO destinataire (call Server.sendTo(String pseudo) )
         */
        dest.delete();
    }

    /**
     * Converting text to image
     * @param text
     */
    public void textToImage(String text) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        try {
            ImageIO.write(img, "bmp", new File("cover.bmp"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
