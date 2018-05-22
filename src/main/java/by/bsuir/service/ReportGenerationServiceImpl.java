package by.bsuir.contract;

import by.bsuir.model.*;
import com.aspose.words.*;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.contract;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@contract("reportGenerationservice")
public class ReportGenerationServiceImpl implements ReportGenerationcontract {
    private final String PAYMENTS_FILE_NAME = "./Payments report.csv";
    private final String USER_contractS_FILE_NAME = "./User contracts report.csv";
    private final String employer_contractS_FILE_NAME = "./Employer contracts.csv";
    private final String USER_CONTRACT_FILE_TEMPLATE = "User Contract.docx";
    private final String PAYMENT_QUOTE_FILE_TEMPLATE = "Payment Quote.docx";

    public ReportGenerationServiceImpl() throws Exception {
        try {
            License license = new License();
            ClassLoader classLoader = getClass().getClassLoader();
            license.setLicense(classLoader.getResourceAsStream("Aspose.Words.lic"));
        } catch (FileNotFoundException e) {
            throw new Exception("Aspose license file not found", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Aspose license activating exception", e);
        }
    }

    @Override
    public void generateUserPaymentsReport(List<PaymentEntity> payments) throws IOException {
        String[] headers = new String[]{"Employer Id", "Amount", "Date"};

        List<String[]> paymentsData = new ArrayList();
        for (PaymentEntity transaction: payments) {
            paymentsData.add(new String[]{
                transaction.getemployer_id().toString(), transaction.getAmount().toString(), transaction.getDate().toString()
            });
        }

        generateCsvFile(PAYMENTS_FILE_NAME, headers, paymentsData);
    }

    @Override
    public void generateUserServicesReport(User user) throws IOException {
        String[] headers = new String[]{"Title", "Description", "daysNumber Code", "Cost Per Month"};

        List<String[]> contractsData = new ArrayList();
        for (ContractEntity contract: user.getcontracts()) {
            contractsData.add(new String[]{
                    contract.getTitle(), contract.getDescription(), contract.getdaysNumber(), contract.getCostPerMonth().toString()
            });
        }

        generateCsvFile(USER_contractS_FILE_NAME, headers, contractsData);
    }

    @Override
    public void generateListOfAllemployercontracts(Employer employer) throws IOException {
        String[] headers = new String[]{"Title", "Description", "daysNumber Code", "Cost Per Month"};

        List<String[]> contractsData = new ArrayList();
        for (ContractEntity contract: employer.getcontracts()) {
            contractsData.add(new String[]{
                    contract.getTitle(), contract.getDescription(), contract.getdaysNumber(), contract.getCostPerMonth().toString()
            });
        }

        generateCsvFile(employer_contractS_FILE_NAME, headers, contractsData);
    }

    @Override
    public void generateUserService(OutputStream stream, User user, ThingEntity thing, Date date) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Document docTemplate = new Document(classLoader.getResourceAsStream(USER_CONTRACT_FILE_TEMPLATE));

        int totalCost = 0;
        for (ContractEntity contract: thing.getcontracts()) {
            totalCost += contract.getCostPerMonth();
        }

        String finalTotalCost = Integer.toString(totalCost);
        Map<String, String> fieldValues = new HashMap<String, String>(){{
            put("employerTitle", thing.getOwner().getTitle());
            put("thingTitle", thing.getTitle());
            put("thingDescription", thing.getDescription());
            put("UserName", user.getFio());
            put("UserEmail", user.getEmail());
            put("ContractDate", date.toString());
            put("TotalCost", finalTotalCost);
        }};

        List<Map<String, String>> tableFieldValues = new ArrayList<Map<String, String>>();
        for (ContractEntity contract: thing.getcontracts()) {
            tableFieldValues.add(new HashMap<String, String>() {{
                put("contractTitle", contract.getTitle());
                put("contractDescription", contract.getDescription());
                put("contractCost", contract.getCostPerMonth().toString());
            }});
        }

        fillValuesInDocumentTemplate(docTemplate, fieldValues);
        fillTableValuesInDocumentTemplate(docTemplate, "contractsTable", tableFieldValues);

        docTemplate.save(stream, SaveFormat.PDF);
    }

    @Override
    public void generatePaymentQuote(User user, PaymentEntity payment, Employer employer, Date date) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Document docTemplate = new Document(classLoader.getResourceAsStream(PAYMENT_QUOTE_FILE_TEMPLATE));

        Map<String, String> fieldValues = new HashMap<String, String>(){{
            put("Employer", employer.getTitle());
            put("employerDescription", employer.getDescription());
            put("UserName", user.getFio());
            put("UserEmail", user.getEmail());
            put("PaymentAmount", payment.getAmount().toString());
            put("PaymentId", payment.getId().toString());
            put("PaymentDate", date.toString());
        }};

        fillValuesInDocumentTemplate(docTemplate, fieldValues);
    }

    private void generateCsvFile(String fileName, String[] headers, List<String[]> data) throws IOException {
        try (
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(fileName), "utf-8"));
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            if (headers != null && headers.length > 0) {
                csvWriter.writeNext(headers);
            }
            csvWriter.writeAll(data);
            csvWriter.flush();
        }
    }

    private void fillValuesInDocumentTemplate(Document doc, Map<String, String> values) throws Exception {
        BookmarkCollection bookmarks = doc.getRange().getBookmarks();
        for (Map.Entry<String, String> bookmarkValue: values.entrySet()) {
            Bookmark targetBookmark = bookmarks.get(bookmarkValue.getKey());
            if (targetBookmark != null) {
                targetBookmark.setText(bookmarkValue.getValue() != null
                        ? bookmarkValue.getValue()
                        : ""
                );
            }
        }
    }

    private void fillTableValuesInDocumentTemplate(Document doc, String tableBookmarkName, List<Map<String, String>> tableItems) throws Exception {
        Bookmark tableBookmark = doc.getRange().getBookmarks().get(tableBookmarkName);
        Node tableNode = tableBookmark.getBookmarkStart();
        ArrayList tables = new ArrayList();

        while (tableNode != tableBookmark.getBookmarkEnd()) {
            if (tableNode.getNodeType() == NodeType.CELL) {
                tables.add(tableNode.getParentNode().getParentNode());
                tableNode = tableNode.getNextSibling();
                continue;
            }
            tableNode = tableNode.nextPreOrder(doc);
        }
        if (tables.size() != 0) {
            Table table = (Table) tables.get(0);
            for (Map<String, String> rowItems: tableItems) {
                Row newRow = CreateTableRow(doc, table, rowItems);
                table.insertBefore(newRow, table.getLastRow());
            }
        }
    }

    private Row CreateTableRow(Document doc, Table table, Map<String, String> rowItems) {
        Row firstBookmark = table.getFirstRow();
        Row clonedRow = (Row)firstBookmark.deepClone(true);
        for (Cell cell: clonedRow.getCells()) {
            cell.removeAllChildren();
            cell.ensureMinimum();
            cell.getCellFormat().clearFormatting();
            cell.getCellFormat().setWrapText(true);

            Bookmark headerKey = firstBookmark.getRange().getBookmarks().get(clonedRow.indexOf(cell));
            Object valueToInsert = rowItems.get(headerKey.getName());
            if (valueToInsert != null)
            {
                cell.appendChild(new Paragraph(doc));
                cell.getFirstParagraph().appendChild(new Run(doc, valueToInsert.toString()));
            }
        }
        return clonedRow;
    }
}
