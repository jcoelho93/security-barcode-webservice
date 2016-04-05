/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zxinglibrarytest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;

/**
 *
 * @author Camilo
 */
public class ZxingLibraryTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        *   Dummy data
        */
        JSONObject json = new JSONObject();
        json.put("doc_id", 1);
        json.put("doc_page", "3/5");
        json.put("nome", "José Camilo Fernandes Coelho");
        json.put("conta", "000700002696");
        json.put("montante", 143.30);
        json.put("moeda", "EUR");
        
        String data = json.toJSONString();
        System.out.println("JSON" + data);
        
        /*
        *   Hashing dummy data
        */
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(json.toJSONString().getBytes());
            byte hash[] = md.digest();
            // Hash bytes to Hex
            StringBuffer buffer = new StringBuffer();
            for(int i = 0;i< hash.length;i++){
                buffer.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            data = buffer.toString();
            System.out.println("Hash: " + data);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        /*
        *   Barcode Writers
        */
        QRCodeWriter qr_writer = new QRCodeWriter();
        DataMatrixWriter dm_writer = new DataMatrixWriter();
        PDF417Writer pdf_writer = new PDF417Writer();
        
        /*
        *   Barcode matrices
        */
        BitMatrix[] codes = new BitMatrix[3];
        
        /*
        *   Barcode hints (settings)
        */
        Map qr_hints = new HashMap();
        qr_hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        qr_hints.put(EncodeHintType.MARGIN,5);
        
        /*
        *   Barcode encoding
        */
        try{
            
            codes[0] = qr_writer.encode(data, BarcodeFormat.QR_CODE, 150, 150, qr_hints);
            codes[1] = dm_writer.encode(data, BarcodeFormat.DATA_MATRIX, 150, 150);
            codes[2] = pdf_writer.encode(data, BarcodeFormat.PDF_417, 150, 150);
            
        }catch(WriterException writer_e){
            writer_e.printStackTrace();
        }
        
        /*
        *   Barcode file paths
        */
        Path qr_path = FileSystems.getDefault().getPath("C:\\exictos-qrcode-module\\tests\\ZxingLibraryTest\\images","\\qrcode.png");
        Path dm_path = FileSystems.getDefault().getPath("C:\\exictos-qrcode-module\\tests\\ZxingLibraryTest\\images","\\datamatrix.png");
        Path pdf_path = FileSystems.getDefault().getPath("C:\\exictos-qrcode-module\\tests\\ZxingLibraryTest\\images","\\pdf417.png");
        
        /*
        *   Writing barcodes to paths
        */
        try{
            
            MatrixToImageWriter.writeToPath(codes[0], "png", qr_path);
            MatrixToImageWriter.writeToPath(codes[1], "png", dm_path);
            MatrixToImageWriter.writeToPath(codes[2], "png", pdf_path);
            
            System.out.println("Codes Generated");
            
        }catch(IOException io_e){
            io_e.printStackTrace();
        }
        
        /*
        *   Reading the barcode
        */        
        QRCodeReader reader = new QRCodeReader();
        String file = "C:\\exictos-qrcode-module\\tests\\ZxingLibraryTest\\images\\teste.png";
        BinaryBitmap binaryBitmap = null;
        
        /*
        *   Create a binary bitmap from file
        */
        try {
            binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(file)))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Result res = null;
        /*
        *   Decode barcode
        */
        try{
            res = reader.decode(binaryBitmap);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println(res.getText());
        
    }
    
}
