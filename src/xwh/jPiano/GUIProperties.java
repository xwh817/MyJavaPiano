package xwh.jPiano;

import java.util.Iterator;
import java.util.Properties;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.jtattoo.plaf.AbstractLookAndFeel;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;

public class GUIProperties
{
  public static final String PLAF_METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
  public static final String PLAF_NIMBUS = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
  public static final String PLAF_WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
  public static final String PLAF_MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
  public static final String PLAF_MAC = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
  public static final String PLAF_ACRYL = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
  public static final String PLAF_AERO = "com.jtattoo.plaf.aero.AeroLookAndFeel";
  public static final String PLAF_ALUMINIUM = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
  public static final String PLAF_BERNSTEIN = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
  public static final String PLAF_FAST = "com.jtattoo.plaf.fast.FastLookAndFeel";
  public static final String PLAF_GRAPHITE = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
  public static final String PLAF_HIFI = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
  public static final String PLAF_LUNA = "com.jtattoo.plaf.luna.LunaLookAndFeel";
  public static final String PLAF_MCWIN = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
  public static final String PLAF_MINT = "com.jtattoo.plaf.mint.MintLookAndFeel";
  public static final String PLAF_NOIRE = "com.jtattoo.plaf.noire.NoireLookAndFeel";
  public static final String PLAF_SMART = "com.jtattoo.plaf.smart.SmartLookAndFeel";
  public static final String PLAF_CUSTOM = "de.pnwvi.plaf.PNWLookAndFeel";
  
  //private String lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
  
  private String theme = "Default";
  
  public MetalLookAndFeel currentLAF;

  
  private MyJavaPiano jPiano;
  
  public GUIProperties(MyJavaPiano jPiano){
	  this.jPiano = jPiano;
  }
  
  public void setLookAndFeel(String paramString)
  {
    this.theme = paramString;
  }

  public String getLookAndFeel()
  {
    return this.theme;
  }

  public void setTheme(String paramString)
  {
    this.theme = paramString;
  }

  public String getTheme()
  {
    return this.theme;
  }

  public boolean isMetalLook()
  {
    return this.theme.equals("javax.swing.plaf.metal.MetalLookAndFeel");
  }

  public boolean isNimbusLook()
  {
    return this.theme.equals("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
  }

  public boolean isWindowsLook()
  {
    return this.theme.equals("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
  }

  public boolean isMotifLook()
  {
    return this.theme.equals("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
  }

  public boolean isMacLook()
  {
    return this.theme.equals("com.sun.java.swing.plaf.mac.MacLookAndFeel");
  }

  public boolean isFastLook()
  {
    return this.theme.equals("com.jtattoo.plaf.fast.FastLookAndFeel");
  }

  public boolean isGraphiteLook()
  {
    return this.theme.equals("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
  }

  public boolean isSmartLook()
  {
    return this.theme.equals("com.jtattoo.plaf.smart.SmartLookAndFeel");
  }

  public boolean isAcrylLook()
  {
    return this.theme.equals("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
  }

  public boolean isAeroLook()
  {
    return this.theme.equals("com.jtattoo.plaf.aero.AeroLookAndFeel");
  }

  public boolean isBernsteinLook()
  {
    return this.theme.equals("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
  }

  public boolean isAluminiumLook()
  {
    return this.theme.equals("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
  }

  public boolean isMcWinLook()
  {
    return this.theme.equals("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
  }

  public boolean isMintLook()
  {
    return this.theme.equals("com.jtattoo.plaf.mint.MintLookAndFeel");
  }

  public boolean isHiFiLook()
  {
    return this.theme.equals("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
  }

  public boolean isNoireLook()
  {
    return this.theme.equals("com.jtattoo.plaf.noire.NoireLookAndFeel");
  }

  public boolean isLunaLook()
  {
    return this.theme.equals("com.jtattoo.plaf.luna.LunaLookAndFeel");
  }

  public static boolean isCustomEnabled()
  {
    return false;
  }

  public boolean isCustomLook()
  {
    return this.theme.equals("de.pnwvi.plaf.PNWLookAndFeel");
  }
  
  
  @SuppressWarnings("unchecked")
  public Iterator it_themes = null;
  

	/*public GUIProperties guiProps = new GUIProperties();
	*/
	public void updateLookAndFeel(String paramString)
	  {
	    LookAndFeel localLookAndFeel1;
	    try
	    {
	      boolean bool1 = false;
	      boolean bool2 = false;
	      localLookAndFeel1 = UIManager.getLookAndFeel();
	     // byte b1 = 0;
	     /* if (localLookAndFeel1 instanceof MetalLookAndFeel)
	        b1 = 1;*/
	      
	     
	      if (localLookAndFeel1 instanceof AbstractLookAndFeel)
	        bool1 = AbstractLookAndFeel.getTheme().isWindowDecorationOn();
	      if (paramString.equals("javax.swing.plaf.metal.MetalLookAndFeel"))
	        {
	    	  MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());	        
	        }
	      else if (paramString.equals("com.jtattoo.plaf.fast.FastLookAndFeel"))
	        {
	    	  FastLookAndFeel.setTheme("Default");
	    	  it_themes = FastLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.graphite.GraphiteLookAndFeel"))
	        {
	    	  GraphiteLookAndFeel.setTheme("Default");
	          it_themes = GraphiteLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.smart.SmartLookAndFeel"))
	      {
	    	  SmartLookAndFeel.setTheme("Default");
	          it_themes = SmartLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.acryl.AcrylLookAndFeel"))
	      {
	    	  AcrylLookAndFeel.setTheme("Default");
	          it_themes = AcrylLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.aero.AeroLookAndFeel"))
	      {
	    	  AeroLookAndFeel.setTheme("Default");
	          it_themes = AeroLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"))
	      {
	    	  BernsteinLookAndFeel.setTheme("Default");
	          it_themes = BernsteinLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"))
	      {
	    	  AluminiumLookAndFeel.setTheme("Default");
	          it_themes = AluminiumLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.mcwin.McWinLookAndFeel"))
	      {
	    	  McWinLookAndFeel.setTheme("Default");
	          it_themes = McWinLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.mint.MintLookAndFeel"))
	      {
	    	  MintLookAndFeel.setTheme("Default");
	          it_themes = MintLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.hifi.HiFiLookAndFeel"))
	      {
	    	  HiFiLookAndFeel.setTheme("Default");
	          it_themes = HiFiLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.noire.NoireLookAndFeel"))
	      {
	    	  NoireLookAndFeel.setTheme("Default");
	          it_themes = NoireLookAndFeel.getThemes().iterator();
	        }
	      else if (paramString.equals("com.jtattoo.plaf.luna.LunaLookAndFeel"))
	      {
	    	  LunaLookAndFeel.setTheme("Default");
	          it_themes = LunaLookAndFeel.getThemes().iterator();
	        }


    	  if(jPiano.menuBar!=null){
    		  jPiano.menuBar.setThemeList(it_themes);
    	  }

	      
	      this.setTheme("Default");
	      this.setLookAndFeel(paramString);
	      UIManager.setLookAndFeel(this.getLookAndFeel());
	      LookAndFeel localLookAndFeel2 = UIManager.getLookAndFeel();
	      /*byte b2 = 0;
	      if (localLookAndFeel2 instanceof MetalLookAndFeel)
	        b2 = 1;*/
	      if (localLookAndFeel2 instanceof AbstractLookAndFeel)
	        bool2 = AbstractLookAndFeel.getTheme().isWindowDecorationOn();
	      if (bool1 != bool2)
	      {
	        /*Rectangle localRectangle = getBounds();
	        dispose();
	        app = new JTattooDemo(localRectangle);
	        app.setBounds(localRectangle);*/
	      }
	      else
	      {
	    	  Properties props = new Properties();
	    	  props.put("logoString", "MyJavaPiano"); 
        
	    	  LookAndFeel localLookAndFeel = UIManager.getLookAndFeel();
		        if (localLookAndFeel instanceof FastLookAndFeel)
		          FastLookAndFeel.setCurrentTheme(props);
		        if (localLookAndFeel instanceof GraphiteLookAndFeel)
		          GraphiteLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof SmartLookAndFeel)
		          SmartLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof AcrylLookAndFeel)
		          AcrylLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof AeroLookAndFeel)
		          AeroLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof BernsteinLookAndFeel)
		          BernsteinLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof AluminiumLookAndFeel)
		          AluminiumLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof McWinLookAndFeel)
		          McWinLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof MintLookAndFeel)
		          MintLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof HiFiLookAndFeel)
		          HiFiLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof NoireLookAndFeel)
		          NoireLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof LunaLookAndFeel)
		          LunaLookAndFeel.setCurrentTheme(props);
		        else if (localLookAndFeel instanceof MetalLookAndFeel)
		          MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());

		    jPiano.getRootPane().updateUI();
	        SwingUtilities.updateComponentTreeUI(jPiano);
	        if(jPiano.recordFrame!=null){
	        	 SwingUtilities.updateComponentTreeUI(jPiano.recordFrame);
	        }
	       
	        ConfigManage.initial_props.put("lookAndFeel", paramString);
	      }
	    }
	    catch (Exception localException)
	    {
	      System.out.println("Failed loading L&F: " + this.getLookAndFeel() + " Exception: " + localException.getMessage());
	      localException.printStackTrace();
	    }
	  }
	


	  public void updateTheme(String paramString)
	  {
	    if (paramString != null)
	      try
	      {
	    	
	    	  
	    	//this.setTheme(paramString);
	
	        LookAndFeel localLookAndFeel = UIManager.getLookAndFeel();
	        if (localLookAndFeel instanceof FastLookAndFeel)
	          FastLookAndFeel.setTheme(paramString, "", "java piano");
	        if (localLookAndFeel instanceof GraphiteLookAndFeel)
	          GraphiteLookAndFeel.setTheme(paramString);
	        else if (localLookAndFeel instanceof SmartLookAndFeel)
	          SmartLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof AcrylLookAndFeel)
	          AcrylLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof AeroLookAndFeel)
	          AeroLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof BernsteinLookAndFeel)
	          BernsteinLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof AluminiumLookAndFeel)
	          AluminiumLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof McWinLookAndFeel)
	          McWinLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof MintLookAndFeel)
	          MintLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof HiFiLookAndFeel)
	          HiFiLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof NoireLookAndFeel)
	          NoireLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof LunaLookAndFeel)
	          LunaLookAndFeel.setTheme(paramString, "", "java piano");
	        else if (localLookAndFeel instanceof MetalLookAndFeel)
	          MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
	        	
	        jPiano.getRootPane().updateUI();
	        SwingUtilities.updateComponentTreeUI(jPiano);
	        if(jPiano.recordFrame!=null){
	        	 SwingUtilities.updateComponentTreeUI(jPiano.recordFrame);
	        }
	        
	        ConfigManage.initial_props.put("theme", paramString);
	        
	      }
	    catch(NullPointerException nullExp){
	    	ConfigManage.initial_props.put("theme", "Default");	        
	    }
	      catch (Exception localException)
	      {
	        System.out.println("Failed setting theme! Exception: " + localException.getMessage());
	        localException.printStackTrace();
	      }
	  }

	  
	  
  
}