package cn.ylw.common.rtf;

import com.aspose.words.Body;
import com.aspose.words.Cell;
import com.aspose.words.CellCollection;
import com.aspose.words.Document;
import com.aspose.words.ImageData;
import com.aspose.words.Node;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.Row;
import com.aspose.words.RowCollection;
import com.aspose.words.Run;
import com.aspose.words.Section;
import com.aspose.words.SectionCollection;
import com.aspose.words.Shape;
import com.aspose.words.Table;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;

/**
 * rtf解析
 *
 * @author yanluwei
 * @date 2021/7/13
 */
public class RTFUtil {

    public static void rtf2docx() throws Exception {

        // poi操作word
        XWPFDocument document = new XWPFDocument();

        Document rtf = new Document("D://test.rtf");

        // 获取section
        SectionCollection sections = rtf.getSections();
        for (Section section : sections) {
            // 获取body
            Body body = section.getBody();
            NodeCollection childNodes = body.getChildNodes();
            Node[] nodes = childNodes.toArray();
            for (Node node : nodes) {
                // 获取节点类型
                int nodeType = node.getNodeType();
                switch (nodeType) {
                    case NodeType.PARAGRAPH:
                        Paragraph paragraph = (Paragraph) node;
                        // 获取子节点
                        NodeCollection paragraphChildNodes = paragraph.getChildNodes();
                        Node[] paragraphChild = paragraphChildNodes.toArray();

                        // poi操作word
                        XWPFParagraph documentParagraph = document.createParagraph();


                        for (Node pNode : paragraphChild) {
                            int pNodeType = pNode.getNodeType();
                            switch (pNodeType) {
                                case NodeType.RUN:
                                    Run run = (Run) pNode;

                                    // poi操作word
                                    XWPFRun documentParagraphRun = documentParagraph.createRun();
                                    documentParagraphRun.setText(run.getText());
                                    break;
                                case NodeType.SHAPE:
                                    Shape shape = (Shape) pNode;
                                    if (shape.hasImage()) {
                                        ImageData imageData = shape.getImageData();

                                        // poi操作word
                                        XWPFParagraph p = document.createParagraph();
                                        XWPFRun pRun = p.createRun();
                                        pRun.addPicture(new ByteArrayInputStream(imageData.getImageBytes()),
                                            XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(100), Units.toEMU(100));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        String paragraphText = paragraph.getText();


                        break;
                    case NodeType.TABLE:

                        Table table = (Table) node;
                        // poi操作word
                        XWPFTable documentTable = document.createTable();
                        CTTblWidth infoTableWidth = documentTable.getCTTbl().addNewTblPr().addNewTblW();
                        infoTableWidth.setType(STTblWidth.DXA);
                        infoTableWidth.setW(BigInteger.valueOf(9072));

                        RowCollection rows = table.getRows();
                        int i = 0;
                        for (Row row : rows) {
                            CellCollection cells = row.getCells();

                            // poi操作word
                            XWPFTableRow documentTableRow = documentTable.getRow(i);
                            if (documentTableRow == null) {
                                documentTableRow = documentTable.createRow();
                            }


                            int j = 0;
                            for (Cell cell : cells) {
                                String text = cell.getText().trim();

                                // poi操作word
                                XWPFTableCell documentTableRowCell = documentTableRow.getCell(j);
                                if (documentTableRowCell == null) {
                                    documentTableRowCell = documentTableRow.createCell();
                                }
                                documentTableRowCell.setText(text);
                                j++;
                            }
                            i++;
                        }
                        break;
                    default:
                        break;
                }
            }
        }


        // 保存docx
        File file = new File("D://test.docx");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        // poi操作word
        document.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
        rtf2docx();
    }
}
