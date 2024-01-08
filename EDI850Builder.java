import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EDI850Builder {

    public static void main(String[] args) {
        // Sample data for the Purchase Order
        String buyerName = "Buyer Inc.";
        String buyerId = "12345";
        String vendorName = "Vendor Corp.";
        String vendorId = "67890";
        String poNumber = "PO123456";
        String itemNumber = "ITEM001";
        int quantity = 100;
        double price = 10.99;

        // Create an EDI 850 Purchase Order document
        String edi850 = buildEDI850(buyerName, buyerId, vendorName, vendorId, poNumber, itemNumber, quantity, price);

        // Write the EDI document to a file or send it
        try (PrintWriter writer = new PrintWriter(new FileWriter("EDI850.txt"))) {
            writer.println(edi850);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String buildEDI850(String buyerName, String buyerId, String vendorName, String vendorId,
                                     String poNumber, String itemNumber, int quantity, double price) {
        // Create the EDI 850 segments
        String isaSegment = "ISA*00*          *00*          *ZZ*SENDERID       *ZZ*RECEIVERID     *210101*1234*U*00401*000000001*0*T*:~";
        String gsSegment = "GS*PO*SENDERID*RECEIVERID*20210101*1234*1*X*004010~";
        String stSegment = "ST*850*0001~";
        String bsnSegment = "BSN*00*PO123456*20210101*123456~";
        String n1SegmentBuyer = "N1*BY*Buyer Inc.*01*12345~";
        String n1SegmentVendor = "N1*SE*Vendor Corp.*01*67890~";
        String po1Segment = "PO1*1*100*EA*10.99*UK*ITEM001*VP*PO123456~";
        String cttSegment = "CTT*1~";
        String seSegment = "SE*8*0001~";
        String geSegment = "GE*1*1~";
        String ieaSegment = "IEA*1*000000001~";

        // Concatenate segments to form the EDI document
        return isaSegment + gsSegment + stSegment + bsnSegment + n1SegmentBuyer + n1SegmentVendor + po1Segment + cttSegment + seSegment + geSegment + ieaSegment;
    }
}
