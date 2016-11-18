package selmibenromdhane.sparta_v1.utils;

import android.content.res.AssetManager;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;

public class Utils {

	public static void changeFont(AssetManager assets, TextView txt1) {
		// TODO Auto-generated method stub
//		Typeface font = Typeface.createFromAsset(assets, "fonts/klavik.ttf");
//		txt1.setTypeface(font);
	}
	
	public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
	
}
