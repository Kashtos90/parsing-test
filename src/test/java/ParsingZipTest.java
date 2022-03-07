import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingZipTest {

    private static final String
            CSV = "example CSV.csv",
            XLSX = "example XLSX.xlsx",
            PDF = "example PDF.pdf";

    @Test
    void readZip() throws Exception {
        ZipFile zF = new ZipFile("src/test/resources/examples.zip");
        Enumeration<? extends ZipEntry> entries = zF.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getName().contains("csv")) {
                assertThat(entry.getName()).isEqualTo(CSV);
                parseCsvTest(zF.getInputStream(entry));
            } else if (entry.getName().contains("xlsx")) {
                assertThat(entry.getName()).isEqualTo(XLSX);
                parseXlsTest(zF.getInputStream(entry));
            } else if (entry.getName().contains("pdf")) {
                assertThat(entry.getName()).isEqualTo(PDF);
                parsePdfTest(zF.getInputStream(entry));
            }
        }
    }

    void parseCsvTest(InputStream file) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file));) {
            List<String[]> strA = reader.readAll();
            assertThat(strA.get(0)).contains(
                    "0;First Name;Last Name;Gender;Country;Age;Date;Id"
            );
        }
    }

    void parseXlsTest(InputStream file) throws Exception {
        XLS xls = new XLS(file);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(10)
                .getCell(1)
                .getStringCellValue()).contains("Fallon");

    }

    void parsePdfTest(InputStream file) throws Exception {
        PDF pdf = new PDF(file);
        assertThat(pdf.text).contains(
                "Анимационная деятельность в туризме"
        );

    }

}
