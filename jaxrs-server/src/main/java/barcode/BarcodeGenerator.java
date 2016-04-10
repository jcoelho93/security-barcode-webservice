/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.swagger.model.InputDataSettings;
import io.swagger.model.Setting;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Camilo
 */
public class BarcodeGenerator {

    private InputDataSettings settings;
    private BitMatrix barcodeMatrix;
    
    public BarcodeGenerator(){
        
        this.settings = null;
        
    }
    
    public BarcodeGenerator(InputDataSettings settings){
        
        this.settings = settings;
        
    }

    public BufferedImage generateBarcode(String data) throws WriterException{
        
        Map hints = new HashMap();
        
        String type = this.settings.getBarcode().getType().toString();
        
        if(type.equals("qr_code")){
            
            QRCodeWriter qr_writer = new QRCodeWriter();
            
            //Define QR Code Error Correction Level
            String ecl = this.settings.getBarcode().getEcl().toString();
            if(ecl.equals("h")){
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            }else if(ecl.equals("l")){
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            }else if(ecl.equals("m")){
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            }else{
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
            }
            
            if(this.settings.getBarcode().getMargin() != null){
                hints.put(EncodeHintType.MARGIN, this.settings.getBarcode().getMargin());
            }else{
                hints.put(EncodeHintType.MARGIN, 3);
            }
            
            int width;
            int height;
            
            if(this.settings.getBarcode().getWidth() != null){
                width = this.settings.getBarcode().getWidth();
            }else{
                width = 100;
            }
            
            if(this.settings.getBarcode().getHeight() != null){
                height = this.settings.getBarcode().getHeight();
            }else{
                height = 100;
            }
            
            this.barcodeMatrix = qr_writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);
            
        }else if(type.equals("data_matrix")){
            
            DataMatrixWriter dm_writer = new DataMatrixWriter();
            
            String shape = this.settings.getBarcode().getShape().toString();
            
            // Defines Data Matrix shape
            if(shape.equals("force_none")){
                hints.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_NONE);
            }else if(shape.equals("force_rectangle")){
                hints.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_RECTANGLE);
            }else{
                hints.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE);
            }
            
            if(this.settings.getBarcode().getMargin() != null){
                hints.put(EncodeHintType.MARGIN, this.settings.getBarcode().getMargin());
            }else{
                hints.put(EncodeHintType.MARGIN, 3);
            }
            
            int width;
            int height;
            
            if(this.settings.getBarcode().getWidth() != null){
                width = this.settings.getBarcode().getWidth();
            }else{
                width = 100;
            }
            
            if(this.settings.getBarcode().getHeight() != null){
                height = this.settings.getBarcode().getHeight();
            }else{
                height = 100;
            }
            
            this.barcodeMatrix = dm_writer.encode(data, BarcodeFormat.DATA_MATRIX, width, height, hints);
            
        }else{
            
            PDF417Writer pdf_writer = new PDF417Writer();
            
            boolean compact = this.settings.getBarcode().getCompact();
            String compaction = this.settings.getBarcode().getCompaction().toString();
            
            // Makes sure compact isn't null
            if(compact){
                hints.put(EncodeHintType.PDF417_COMPACT, compact);
            }
            
            hints.put(EncodeHintType.PDF417_COMPACTION, Compaction.valueOf(compaction.toUpperCase()));
            
            if(this.settings.getBarcode().getMargin() != null){
                hints.put(EncodeHintType.MARGIN, this.settings.getBarcode().getMargin());
            }else{
                hints.put(EncodeHintType.MARGIN, 3);
            }
            
            int width;
            int height;
            
            if(this.settings.getBarcode().getWidth() != null){
                width = this.settings.getBarcode().getWidth();
            }else{
                width = 100;
            }
            
            if(this.settings.getBarcode().getHeight() != null){
                height = this.settings.getBarcode().getHeight();
            }else{
                height = 100;
            }
            
            this.barcodeMatrix = pdf_writer.encode(data, BarcodeFormat.PDF_417, width, height, hints);

        }
        
        return MatrixToImageWriter.toBufferedImage(this.barcodeMatrix);
    }
    
    public InputDataSettings getSettings() {
        return this.settings;
    }

    public void setSettings(InputDataSettings settings) {
        this.settings = settings;
    }   
    
}
