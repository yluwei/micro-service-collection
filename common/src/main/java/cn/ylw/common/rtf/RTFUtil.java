package cn.ylw.common.rtf;

import com.aspose.words.Body;
import com.aspose.words.Cell;
import com.aspose.words.CellCollection;
import com.aspose.words.Document;
import com.aspose.words.ImageData;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphCollection;
import com.aspose.words.Row;
import com.aspose.words.RowCollection;
import com.aspose.words.Section;
import com.aspose.words.SectionCollection;
import com.aspose.words.Shape;
import com.aspose.words.Table;
import com.aspose.words.TableCollection;
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

        XWPFDocument document = new XWPFDocument();

        Document rtf = new Document("D://test.rtf");

        SectionCollection sections = rtf.getSections();
        for (Section next : sections) {
            Body body = next.getBody();
            // 读取段落
            ParagraphCollection paragraphs = body.getParagraphs();
            for (Paragraph paragraph : paragraphs) {
                XWPFParagraph documentParagraph = document.createParagraph();
                String text = paragraph.getText();
                XWPFRun run = documentParagraph.createRun();
                run.setText(text);
            }

            // 读取表格
            TableCollection tables = body.getTables();
            for (Table table : tables) {
                XWPFTable documentTable = document.createTable();
                CTTblWidth infoTableWidth = documentTable.getCTTbl().addNewTblPr().addNewTblW();
                infoTableWidth.setType(STTblWidth.DXA);
                infoTableWidth.setW(BigInteger.valueOf(9072));
                RowCollection rows = table.getRows();
                int i = 0;
                for (Row row : rows) {
                    CellCollection cells = row.getCells();
                    XWPFTableRow documentTableRow = documentTable.getRow(i);
                    if (documentTableRow == null) {
                        documentTableRow = documentTable.createRow();
                    }
                    int j = 0;
                    for (Cell cell : cells) {
                        String text = cell.getText().trim();
                        XWPFTableCell documentTableRowCell = documentTableRow.getCell(j);
                        if (documentTableRowCell == null) {
                            documentTableRowCell = documentTableRow.createCell();
                        }
                        documentTableRowCell.setText(text);
                        j++;
                    }
                    i++;
                }
            }

            // 读取图片
            NodeCollection childNodes = next.getChildNodes(NodeType.SHAPE, true);
            for (Shape shape : (Iterable<Shape>) childNodes) {
                if (shape.hasImage()) {
                    ImageData imageData = shape.getImageData();
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.addPicture(new ByteArrayInputStream(imageData.getImageBytes()),
                        XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(100), Units.toEMU(100));
                }
            }

            // 保存docx
            File file = new File("D://test.docx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            document.write(fileOutputStream);
            fileOutputStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        rtf2docx();
    }
}
