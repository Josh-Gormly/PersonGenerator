import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator
{
    String ID = "";
    String firstName = "";
    String lastName = "";
    String title = "";
    int yearOfBirth = 0;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        ArrayList<String>recs = new ArrayList<>();


        boolean done = false;
        do
        {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the person's ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter the person's first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter the person's last name");
            String title = SafeInput.getNonZeroLenString(in, "Enter the person's title");
            int yearOfBirth = SafeInput.getRangedInt(in, "Enter the person's birth year", 1000, 2020);
            recs.add(ID + "," + firstName + "," + lastName + "," + title + "," + yearOfBirth);
            done = SafeInput.getYNConfirm(in, "Do you want to keep entering information? ");
        }
        while (done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written! ");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}