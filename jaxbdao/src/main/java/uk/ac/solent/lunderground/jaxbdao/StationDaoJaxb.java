package uk.ac.solent.lunderground.jaxbdao;

import java.io.File;

public class StationDaoJaxb
{
    private final String url;

    public StationDaoJaxb(String resourceURL)
    {
        url = resourceURL;
    }

    public boolean load()
    {
        File file = new File(url).getAbsoluteFile();
        System.out.println(file.getAbsolutePath());
        return file.exists();
    }
}
