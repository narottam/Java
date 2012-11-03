//package e;
//import javax.swing.SwingConstants;
//import javax.swing.SwingUtilities;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.UIManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.filechooser.FileNameExtensionFilter;
class MainWindow extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
	
	
			
			if(EditerDemo.ftab.getSelectedIndex()!=-1)
			{
				int respond;
			respond=JOptionPane.showConfirmDialog(EditerDemo.f,"Have you Saved your all file",
			"Respond",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(respond==JOptionPane.YES_OPTION)
			{
				Window w=we.getWindow();
				w.setVisible(false);
				
				w.dispose();
				System.exit(1);
			}
			else
			{}
		
		}
		else
		{
			Window w=we.getWindow();
				w.setVisible(false);
				
				w.dispose();
				System.exit(1);
		}	
	
	
		
	}
}


class RunProgram extends Thread
{
	String aa[];
	File file;
	
	
	public RunProgram(String []aa,File file)
	{
		this.aa=aa;
		this.file=file;
	}
	
public void run()
		{
			
	String tempc="",tempr="";		
			
try
{ 
Runtime rt =Runtime.getRuntime();
Process proc = rt.exec(aa,null,file);

} catch (Throwable t)
{
t.printStackTrace();
}

}

}

class FileMenuOperation implements ActionListener 
{
	public String sfn,temp;
	JFrame f1;
	int ch=0,ch1=0,re=6;
	JTextArea fds;
	JTextField tf,tfr;
	public void actionPerformed(ActionEvent e7)
	{
		String str=e7.getActionCommand();
		
		if(str.equals("Replace All"))
		{
			String ss,fr,wr;
			wr=tfr.getText();
			fr=tf.getText();
			ss=temp.replaceAll(fr,wr);
			fds.setText(ss);
	
		}
		else if(str.equals("Cancel"))
		{
			f1.dispose();
		}
		else if(str.equals("Find Next"))
		{
			String fi;
			fi=tf.getText();
			
			if(!EditerDemo.re.equals(fi))
			{
				EditerDemo.i=0;
				re=6;
			}
			EditerDemo.re=fi;
			String rep;
			//rep=temp.replaceAll("\r","");
			rep=temp.replaceAll("\t","");
			Pattern p=Pattern.compile(fi);
			Matcher m=p.matcher(rep);
			while(m.find())
			{
			ch=m.start();
			ch1=m.end();
			
			if(ch>EditerDemo.i)
				{
				re=6;
				EditerDemo.i=ch;
				break;
				}
				
			}
			
		
			fds.select(ch,ch1);
			//EditerDemo.f.toFront();
			EditerDemo.f.setFocusable(true);
			
		}
		else if(str.equals("Replace"))
		{
			String rep;
			rep=temp.replaceAll("\r","");
			String wr;
			wr=tfr.getText();
			int x;
			x=wr.length();		
			if(re==6)
			{
			
			StringBuffer sb=new StringBuffer(rep);
			sb.delete(ch,ch1);
			sb.insert(ch,wr);
			temp=new String(sb);
			fds.setText(temp);
			fds.select(ch,x);
			re=66;
			
			}
		}
		
	}
//this will find selected word	
	public void find(String ob,JTextArea mf)
	{	
		fds=mf;
		temp=ob;
		EditerDemo.re="";
		JButton b2,b5;
		f1=new JFrame("Find");
			//f1.setSize(200,100);
			f1.setSize(200,100);
			f1.setLocationRelativeTo(EditerDemo.f);
			
			f1.setFont(new Font("",Font.BOLD,15));
			JPanel p=new JPanel();
			
			f1.add(new JLabel("Find What:"),BorderLayout.WEST);
			f1.setResizable(false);

f1.setDefaultCloseOperation(f1.DISPOSE_ON_CLOSE);
			
			
			tf=new JTextField();
			f1.add(tf);
			
			b2=new JButton("Find Next");
			b2.addActionListener(this);
			p.add(b2);
			b5=new JButton("Cancel");
			b5.addActionListener(this);
			p.add(b5);
			f1.add(new Label(),BorderLayout.EAST);
			f1.add(p,BorderLayout.SOUTH);
			
			f1.setVisible(true);
		
	}
//this will find selected word and then replace	
	public String replace(String ob,JTextArea mf)
	{
		fds=mf;
		temp=ob;
		re=6;
		EditerDemo.re="";
		JButton b2,b3,b4,b5;
		f1=new JFrame("Find and Replace");

		f1.setSize(400,180);	
		f1.setLocationRelativeTo(EditerDemo.f);
			
			f1.setFont(new Font("",Font.BOLD,15));
			JPanel p=new JPanel();
			JPanel p1=new JPanel();
			p1.setLayout(new GridLayout(2,1));
			JPanel p2=new JPanel();
			p2.setLayout(new GridLayout(2,1,60,60));
			p1.add(new JLabel("Find What:"));
			p1.add(new JLabel("Replace With:"));
			f1.setResizable(false);
f1.setDefaultCloseOperation(f1.DISPOSE_ON_CLOSE);
			tf=new JTextField();
			tfr=new JTextField();
			p2.add(tf);
			p2.add(tfr);
			b2=new JButton("Find Next");
//call find button
			b2.addActionListener(this);
			p.add(b2);
			b3=new JButton("Replace");
			b3.addActionListener(this);
			p.add(b3);
			b4=new JButton("Replace All");
			b4.addActionListener(this);
			p.add(b4);
			b5=new JButton("Cancel");
			b5.addActionListener(this);
			p.add(b5);
			f1.add(p2);
			f1.add(new Label(),BorderLayout.EAST);
			f1.add(p,BorderLayout.SOUTH);
			f1.add(p1,BorderLayout.WEST);
			f1.setVisible(true);
	return "";
		
	}
	
	public String fileOpen(String ob)
	{
		String ss="";
	try
		{
		File ff=new File(ob);
		if(!ff.exists())
		{JOptionPane.showMessageDialog(f1,ob+"\n"+"File not found\n"+"Please verify the file name","Open",JOptionPane.INFORMATION_MESSAGE);
		EditerDemo.fok=66;
		}
		else
		{	
		EditerDemo.fok=6;
			
			FileInputStream fis=new 
			FileInputStream(ob);
				int ch;
						
				while((ch=fis.read())!=-1)
				ss=ss+(char)ch;
				
		}}
		catch(Exception e5)	
		{ }
		
	return ss;
	}
	
	public void filesave(String ob,String ss)
	{
			temp=ob;
		try{
			File cf=new File(ob);
			cf.createNewFile();
			
			FileOutputStream fos=new FileOutputStream(cf);
			
			char arr[]=ss.toCharArray();
			for(int i=0;i<=arr.length;i++)
				fos.write(arr[i]);
		}
		catch(Exception e8)
		{
		}
	}
	
	
	
	
}

class EditerDemo  implements ActionListener,KeyListener		//,ChangeListener
{	
	static JFrame f;
	JFrame fc;
	JMenuBar mb;
	JMenu m1,m2,m3,m4,m5,m6,mode,m7;
	JMenuItem bld,itlc,nw,opn,abt,compile,code,run,jar,font,normal,html,clear,comment,
	ctab,plain,catab,sve,ext,ct,cpy,pst,find,fnr,sas;
	JCheckBoxMenuItem wrap,state;
	JColorChooser tcolor;
	JTextArea ta,ntemp,otemp;
	static int i=0,fok=0;
	int c=1;
	static String re="";
	HashMap tref,nref,pref,sref,cref;
	Color getcolor;
	String pname,fname,tempr,tempc,modeType;
	static 	JTabbedPane ftab;
	JScrollPane sc,nsp,scr;
	JSplitPane fsp;
	JPanel spt;
	JLabel status,lc;
	JButton cok,cnl;
	JEditorPane jep=null;
//	private UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();	
	public EditerDemo() 
	{
			f=new JFrame("N-Editor");
	/*	try {
      UIManager.setLookAndFeel(looks[2].getClassName());
      SwingUtilities.updateComponentTreeUI(f);
    } catch (Exception e) {
      e.printStackTrace();
    }
		
		*/
		
		/*try{
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}catch(Exception ignore){
}

*/


		try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
		
		
	
		f.setBounds(100,50,600,500);
		f.setDefaultCloseOperation(f.DO_NOTHING_ON_CLOSE);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.png"));

		
		MainWindow mw=new MainWindow();
		f.addWindowListener(mw);
	// it is for html mode
		jep=new JEditorPane();
		jep.setContentType("text/plain");
		modeType="normal";
	//get reference
		tref=new HashMap();			
		sref=new HashMap();			
		sref.put(-1,"not");	
		
		nref=new HashMap();			
		pref=new HashMap();			
			
		cref=new HashMap();			
	
		
	
	
		ta=new JTextArea("");
	
		ta.setFont(new Font("",Font.BOLD,16));
		
		sc=new JScrollPane(ta);
		ftab=new JTabbedPane(JTabbedPane.BOTTOM);
	
		ftab.addTab("Untiled note-1",sc);
		
		ftab.setBorder(BorderFactory.createTitledBorder("JAVA_Mode"));	
			
		tref.put(ftab.getSelectedIndex(),ta);	
			sref.put(ftab.getSelectedIndex(),"unsave");		
			ftab.setToolTipTextAt(ftab.getSelectedIndex(),"unsave");
		
		//result=new JTextArea("");
		scr=new JScrollPane(jep);
		jep.setEditable(false);
	
		ftab.setMinimumSize(new Dimension(500,300));
		ftab.setPreferredSize(new Dimension(500,400));
		scr.setBorder(BorderFactory.createTitledBorder("Result"));	
	
		fsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,ftab,scr);
		
		f.add(fsp);	
	
	
		mb=new JMenuBar();
		m1=new JMenu("File");
			m1.setMnemonic(KeyEvent.VK_F);
		m2=new JMenu("Edit");
			m2.setMnemonic(KeyEvent.VK_E);
		m3=new JMenu("Others");
			m3.setMnemonic(KeyEvent.VK_O);
		m7=new JMenu("Font");
			m7.setMnemonic(KeyEvent.VK_F);
		m6=new JMenu("View");
			m6.setMnemonic(KeyEvent.VK_V);
		m5=new JMenu("Build");
			m5.setMnemonic(KeyEvent.VK_B);
		m4=new JMenu("Help");
			m4.setMnemonic(KeyEvent.VK_H);
		mode=new JMenu("Mode");
			mode.setMnemonic(KeyEvent.VK_M);
		
		
		
		nw=new JMenuItem("New");
			nw.setMnemonic(KeyEvent.VK_N);
			nw.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
                //ImageIcon icon = createImageIcon("images/middle.gif");
        //menuItem = new JMenuItem("Both text and icon", icon);
                
                
		opn=new JMenuItem("Open");
			opn.setMnemonic(KeyEvent.VK_O);
			opn.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		sve=new JMenuItem("Save");
			sve.setMnemonic(KeyEvent.VK_S);
			sve.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		sas=new JMenuItem("Save As");
			sas.setMnemonic(KeyEvent.VK_A);
		ctab=new JMenuItem("Close");
			ctab.setMnemonic(KeyEvent.VK_C);
		catab=new JMenuItem("Close All");
			catab.setMnemonic(KeyEvent.VK_L);
		ext=new JMenuItem("Exit");
			ext.setMnemonic(KeyEvent.VK_X);
		ct=new JMenuItem("Cut");
			ct.setMnemonic(KeyEvent.VK_T);
			ct.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cpy=new JMenuItem("Copy");
			cpy.setMnemonic(KeyEvent.VK_C);
			cpy.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		pst=new JMenuItem("Paste");
			pst.setMnemonic(KeyEvent.VK_P);
			pst.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		find=new JMenuItem("Find");
			find.setMnemonic(KeyEvent.VK_I);
		fnr=new JMenuItem("Find and Replace");
			fnr.setMnemonic(KeyEvent.VK_D);
		abt=new JMenuItem("About");
			abt.setMnemonic(KeyEvent.VK_A);
		compile=new JMenuItem("Compile");
			compile.setMnemonic(KeyEvent.VK_C);
		run=new JMenuItem("Run");
			run.setMnemonic(KeyEvent.VK_R);
		jar=new JMenuItem("JAR FILE");
			jar.setMnemonic(KeyEvent.VK_J);
			jar.setEnabled(false);	
		font=new JMenuItem("Colour");
			font.setMnemonic(KeyEvent.VK_T);
		clear=new JMenuItem("Clear Result");
			clear.setMnemonic(KeyEvent.VK_C);
		html=new JMenuItem("Html Mode");
			html.setMnemonic(KeyEvent.VK_H);
		normal=new JMenuItem("JAVA_Mode");
			normal.setMnemonic(KeyEvent.VK_J);
			normal.setEnabled(false);
		code=new JMenuItem("Generate Code");
			code.setMnemonic(KeyEvent.VK_N);
			code.setEnabled(false);	
		comment=new JMenuItem("Comment");
			comment.setMnemonic(KeyEvent.VK_E);
			comment.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_K, ActionEvent.CTRL_MASK));

		code.addActionListener(this);
		jar.addActionListener(this);
		comment.addActionListener(this);
		clear.addActionListener(this);
		html.addActionListener(this);
		normal.addActionListener(this);
		font.addActionListener(this);
		run.addActionListener(this);
		compile.addActionListener(this);
		ctab.addActionListener(this);
		catab.addActionListener(this);
		fnr.addActionListener(this);
		find.addActionListener(this);
		nw.addActionListener(this);
		opn.addActionListener(this);
		sve.addActionListener(this);
		sas.addActionListener(this);
		ext.addActionListener(this);
		ct.addActionListener(this);
		cpy.addActionListener(this);
		pst.addActionListener(this);
		abt.addActionListener(this);
		
		wrap=new JCheckBoxMenuItem("Word wrap");
		state=new JCheckBoxMenuItem("Status bar");
		bld=new JMenuItem("Bold");
		plain=new JMenuItem("Plain");
		itlc=new JMenuItem("Italic");
		bld.setEnabled(false);
		plain.addActionListener(this);
		wrap.addActionListener(this);
		state.addActionListener(this);
		bld.addActionListener(this);
		itlc.addActionListener(this);
		
		m1.add(nw);
		m1.add(opn);
		m1.add(sve);
		m1.add(sas);
		m1.addSeparator();
		m1.add(ctab);
		m1.add(catab);
		m1.addSeparator();
		m1.add(ext);
			m3.add(ct);
			m3.add(cpy);
			m3.add(pst);
				
				
				m7.add(bld);
				m7.add(itlc);
				m7.add(plain);
				m2.add(m7);
				m2.addSeparator();
				m2.add(find);
				m2.add(fnr);
				m2.addSeparator();
				m2.add(m3);
				//m2.addSeparator();
		m6.add(font);
		m6.add(comment);
		m6.add(wrap);
		m6.add(state);
		
			m4.add(abt);
		m5.add(compile);
		m5.add(run);
		m5.add(clear);
		m5.add(jar);
		m5.add(code);
				mode.add(normal);
				mode.add(html);
			mb.add(m1);		
			mb.add(m2);
			mb.add(m6);
			mb.add(mode);
			mb.add(m5);	
			mb.add(m4);	
			f.setJMenuBar(mb);
	
//status	
	status=new JLabel("Welcome",JLabel.LEFT);
	spt=new JPanel(new BorderLayout());
	//spt.add(status,BorderLayout.WEST);
	
	spt.add(new JLabel("Powered by Narottam Goyal",JLabel.RIGHT),BorderLayout.EAST);
	f.add(spt,BorderLayout.SOUTH);
	
	// color chooser
		tcolor=new JColorChooser();
	
			//getcolor=new Color();				
	//key listener 
			// for tab jtextarea	
		ta.addKeyListener(this);		
			
			
			f.setVisible(true);
			ta.grabFocus();     //to get user input focus 
							//and it should be added at the end of all component
							//bcoz it get focus at the end
	}
	
	//keyboard	
	public void keyPressed(KeyEvent et)
	{			
		cref.put(ftab.getSelectedIndex(),"notsave");	
				if(et.getKeyCode()==KeyEvent.VK_F5)
				{
					
			try{
				compile(new FileMenuOperation());
				runMethod();		
				}
				catch(Exception error)
				{	
				}
				}
				
				
	}
	public void keyReleased(KeyEvent et)
	{
		
	cref.put(ftab.getSelectedIndex(),"notsave");
	JTextArea x;
	x=(JTextArea)tref.get(ftab.getSelectedIndex());
			if(modeType.equals("html"))
			{	
				jep.setText(x.getText());
			}
			else if(et.getKeyChar()=='(')
			{
				x.append(" )");
			}
			else if(et.getKeyChar()=='{')
			{
				x.append("\n }");
			}
			else if(et.getKeyCode()==KeyEvent.VK_OPEN_BRACKET)
			{
				x.append(" ]");
			}
			else if(et.getKeyCode()==KeyEvent.VK_SEMICOLON)
			{
				x.append(" \n");
			}
			//KeyStroke stroke = KeyStroke.getKeyStroke("M");

			
	}
	public void keyTyped(KeyEvent et)
	{
	
	cref.put(ftab.getSelectedIndex(),"notsave");
	
	String title;
	title=ftab.getTitleAt(ftab.getSelectedIndex());
	int in;
	in=title.indexOf(" *");
	if(in>0)
		ftab.setTitleAt(ftab.getSelectedIndex(),title.substring(0,in)+" *");	
	else
		ftab.setTitleAt(ftab.getSelectedIndex(),title+" *");	
	
		
		
			if(modeType.equals("html"))
			{	JTextArea x;
					x=(JTextArea)tref.get(ftab.getSelectedIndex());
					jep.setText(x.getText());
				//	System.out.println(jep.getText());
			}
		
		
	}
	
	
	public void saveMethod(FileMenuOperation fm)
	{
		String sad;
		sad=(String)sref.get(ftab.getSelectedIndex());
	
		
		
	if(sad.equals("unsave"))  //if(name=="") also correct
		{
			JFileChooser fd = new JFileChooser("C:\\Documents and Settings\\Narottam_goyal\\Desktop\\");
	int returnVal = fd.showSaveDialog(f);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    
	
    	fname=fd.getSelectedFile().getName();
		pname=fd.getSelectedFile().getParent();
	pname=pname+"\\";
		if(fname!=null) 
		{
			pref.put(ftab.getSelectedIndex(),pname);	
			nref.put(ftab.getSelectedIndex(),fname);	
			sref.put(ftab.getSelectedIndex(),"save");	
		JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());
			fm.filesave(pname+fname,x.getText());
		ftab.setTitleAt(ftab.getSelectedIndex(),fname);	
		ftab.setToolTipTextAt(ftab.getSelectedIndex(),"saved");
		cref.put(ftab.getSelectedIndex(),"save");
		status.setText("file save successfully");
			
		}
		}
		}
		else if(sad.equals("save"))
		{
			String yp,zn;
		yp=(String)pref.get(ftab.getSelectedIndex());
		zn=(String)nref.get(ftab.getSelectedIndex());
			
			
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());
		
			fm.filesave(yp+zn,x.getText());
			
			ftab.setTitleAt(ftab.getSelectedIndex(),zn);	
			ftab.setToolTipTextAt(ftab.getSelectedIndex(),"saved");
			cref.put(ftab.getSelectedIndex(),"save");
			
			status.setText("file save successfully");
		}
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		FileMenuOperation fm=new FileMenuOperation();
		String str=e.getActionCommand();
		
		
		if(str.equals("New"))
		{		
	
		i=0;
		fok=0;
		re="";
		jep.setText("");
		ntemp=new JTextArea("");
		ntemp.addKeyListener(this);
		ntemp.setFont(new Font("",Font.BOLD,16));	
		nsp=new JScrollPane(ntemp);
		ftab.addTab("Untiled note-"+(++c),nsp);
					
		ftab.setSelectedIndex(ftab.getTabCount()-1);
		tref.put(ftab.getSelectedIndex(),ntemp);
		sref.put(ftab.getSelectedIndex(),"unsave");	
	
		ftab.setToolTipTextAt(ftab.getSelectedIndex(),"unsave");
		status.setText("New file");
				
		}
		else if(str.equals("Open"))
		{
				
		JFileChooser fd = new JFileChooser("C:\\Documents and Settings\\Narottam_goyal\\Desktop\\");
	FileNameExtensionFilter filter = new FileNameExtensionFilter("java","java","txt","html");
	fd.setFileFilter(filter);
	
	
	int returnVal = fd.showOpenDialog(f);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    
	
    	fname=fd.getSelectedFile().getName();
		pname=fd.getSelectedFile().getParent();
		String ss="";
			pname=pname+"\\";
			
			
			
			
	
		if(fname!=null)
		{		
		ss=fm.fileOpen(pname+fname);
		if(fok==6)
		{
	
		
			
		otemp=new JTextArea("");
		otemp.addKeyListener(this);
		otemp.setFont(new Font("",0,16));
		otemp.setText(ss);
		otemp.setFont(new Font("",Font.BOLD,16));	
		jep.setText(otemp.getText());
	JScrollPane spTemp=new JScrollPane(otemp);
	ftab.addTab(fname,spTemp);
	ftab.setSelectedIndex(ftab.getTabCount()-1);
	pref.put(ftab.getSelectedIndex(),pname);	
		nref.put(ftab.getSelectedIndex(),fname);	
		sref.put(ftab.getSelectedIndex(),"save");
		tref.put(ftab.getSelectedIndex(),otemp);
		ftab.setToolTipTextAt(ftab.getSelectedIndex(),"recently open");
	
		
		cref.put(ftab.getSelectedIndex(),"save");
		
	status.setText("File open");
		fok=0;
		}
		
		
		}
    }
	}
		else if(str.equals("Save"))
		{
			saveMethod(fm);
		}
		else if(str.equals("Save As"))
		{
			String sad;
		sad=(String)sref.get(ftab.getSelectedIndex());
		if(sad.equals("save")||sad.equals("unsave"))	
		{
		
	JFileChooser fd = new JFileChooser("C:\\Documents and Settings\\Narottam_goyal\\Desktop\\");
	int returnVal = fd.showSaveDialog(f);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    
	
    	fname=fd.getSelectedFile().getName();
		pname=fd.getSelectedFile().getParent();
	pname=pname+"\\";
		if(fname!=null) 
		{
			
			pref.put(ftab.getSelectedIndex(),pname);	
			nref.put(ftab.getSelectedIndex(),fname);	
			sref.put(ftab.getSelectedIndex(),"save");
						
	
		JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());
			fm.filesave(pname+fname,x.getText());
			
		ftab.setTitleAt(ftab.getSelectedIndex(),fname); 
		ftab.setToolTipTextAt(ftab.getSelectedIndex(),"saved");
		cref.put(ftab.getSelectedIndex(),"save");
		status.setText("file saved");
		
		}
		}
		}
		
	}
	else if(str.equals("About"))
		{
		
		JOptionPane.showMessageDialog(f,"N-IDE\n"+"http://www.narottam.hpage.com\n"+"This IDE is developed by Me\n"+"Copyright 2010","About",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("3.png"));
		status.setText("About");
		}
	
	
		else if(str.equals("Exit"))
		{
			
			
				if(ftab.getSelectedIndex()!=-1)
			{
				int respond;
			respond=JOptionPane.showConfirmDialog(f,"Have you Saved your all file",
			"Respond",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(respond==JOptionPane.YES_NO_OPTION)
			{
				System.exit(0);
			}
			else
			{
				
			}
			
		
		status.setText("");
		}
		else
			System.exit(0);
			
			
	
		}
		else if(ftab.getSelectedIndex()!=-1)
		{
			if(str.equals("Find"))
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());			
			fm.find(x.getText(),x);
	
		}
		else if(str.equals("Find and Replace"))
		{
		JTextArea x;
		x=(JTextArea)tref.get(ftab.getSelectedIndex());			
		fm.replace(x.getText(),x);
			
		}
		
		else if(str.equals("Close"))
		{
		
		if(ftab.getSelectedIndex()!=-1)
			try{
			
			String sad,csad;
		sad=(String)sref.get(ftab.getSelectedIndex());
		csad=(String)cref.get(ftab.getSelectedIndex());
		
		if(sad.equals("save"))	
		{
			if(csad.equals("notsave"))	
			{
			int respond;
			respond=JOptionPane.showConfirmDialog(f,"Do you want to save file",
			"Respond",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(respond==JOptionPane.YES_NO_OPTION)
			{
				saveMethod(fm);
			}
			}
			
		ftab.remove(ftab.getSelectedIndex());	
		}	
		else
		{
			int respond;
			respond=JOptionPane.showConfirmDialog(f,"Do you want to save file",
			"Respond",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(respond==JOptionPane.YES_NO_OPTION)
			{
				saveMethod(fm);
		sad=(String)sref.get(ftab.getSelectedIndex());
		csad=(String)sref.get(ftab.getSelectedIndex());
		if(sad.equals("save"))
				ftab.remove(ftab.getSelectedIndex());
			}
			else
			ftab.remove(ftab.getSelectedIndex());
		
			}
				status.setText("");
			}
			catch(Exception gg)
			{
JOptionPane.showMessageDialog(f,"There are no tabs","Caution",JOptionPane.INFORMATION_MESSAGE);

			}		
			
		
			
		}
		else if(str.equals("Close All"))
		{
			if(ftab.getSelectedIndex()!=-1)
			{
				int respond;
			respond=JOptionPane.showConfirmDialog(f,"Have you Saved your all file",
			"Respond",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(respond==JOptionPane.YES_NO_OPTION)
			{
				ftab.removeAll();
			}
			else
			{
				
			}
			
		
		status.setText("");
		}
		}
		else if(str.equals("Cut"))
		{
		JTextArea x;
		x=(JTextArea)tref.get(ftab.getSelectedIndex());			
		x.cut();
		
		
		
		}
		else if(str.equals("Copy"))
		{
		
		JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());			
			x.copy();
		}
		else if(str.equals("Paste"))
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());			
			x.paste();
		
		}
		else if(str.equals("Colour"))
		{
			fc=new JFrame("Choose color");
		fc.setDefaultCloseOperation(fc.DISPOSE_ON_CLOSE);
		fc.setBounds(150,100,500,500);
		fc.setResizable(false);
		JPanel jpc=new JPanel();
		lc=new JLabel("Choose Your Text Color",JLabel.CENTER);
		lc.setForeground(Color.black);
		lc.setOpaque(true);
        lc.setFont(new Font("SansSerif", Font.BOLD, 24));
        lc.setPreferredSize(new Dimension(100, 65));
		lc.setBorder(BorderFactory.createTitledBorder("Banner"));
		
		tcolor.getSelectionModel().addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent cec)
			{
				getcolor=tcolor.getColor();	
				lc.setForeground(getcolor);	
			}
		});
        tcolor.setBorder(BorderFactory.createTitledBorder("Color"));
		
		
		cok=new JButton("Ok");
		cnl=new JButton("Cancel");
		cok.addActionListener(this);
		cnl.addActionListener(this);
		jpc.add(cok);
		jpc.add(cnl);
		fc.add(jpc,BorderLayout.SOUTH);
		
		
		fc.add(lc,BorderLayout.NORTH);
		fc.add(tcolor,BorderLayout.CENTER);
		//fc.add(tcolor, BorderLayout.PAGE_END);
		
		fc.setVisible(true);
			
		}
		else if(e.getSource()==cok)
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());			
			x.setForeground(getcolor);
			fc.dispose();
		}
		else if(e.getSource()==cnl)
		{
			fc.dispose();
		}
		else if(e.getSource()==plain)
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());
		    x.setFont(new Font("",Font.PLAIN,16));		
		    bld.setEnabled(true);
		    itlc.setEnabled(true);	
		}
		else if(str.equals("Bold"))
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());
		    x.setFont(new Font("",Font.BOLD,16));	
		    bld.setEnabled(false);	
		    itlc.setEnabled(true);	
		}
		else if(str.equals("Italic"))
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());
		    x.setFont(new Font("",Font.ITALIC,16));	
		    itlc.setEnabled(false);
		    bld.setEnabled(true);
		}
		else if(e.getSource()==jar)
		{
			jar(fm);
		}
		else if(str.equals("Compile"))
		{
			compile(fm);
		}
		else if(str.equals("Run"))
		{
			compile(fm);
			try{
				runMethod();		
				}
				catch(Exception error)
				{	
				}
		}
		
		else if(str.equals("Word wrap"))
		{
		if(wrap.getState())
		{
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());			
			x.setLineWrap(true);
		}
		else
		{
			
			JTextArea x;
			x=(JTextArea)tref.get(ftab.getSelectedIndex());			
				x.setLineWrap(false);	
			
		}
		
		}
		else if(str.equals("Status bar"))
		{
		if(state.getState())
		{
			//	
			spt.add(status,BorderLayout.WEST);
			f.repaint();
			f.setVisible(true);
		}
		else
		{
			//f.remove(spt);
			spt.remove(status);
			//status.setText("");
			f.repaint();
			f.setVisible(true);
		}
		}
		else if(e.getSource()==clear)
		{
		jep.setText("");
		}
		else if(e.getSource()==comment)
		{
			int x=0,y=0;
			
			JTextArea text;
			text=(JTextArea)tref.get(ftab.getSelectedIndex());			
			
			x=text.getSelectionStart();
			y=text.getSelectionEnd();
			if(y!=x)
			if(!normal.isEnabled())
			{
				text.insert(" /* ",x);
				text.insert(" */ ",y+4);
			}
			else
			{
				text.insert(" <!-- ",x);
				text.insert(" --> ",y+6);
			}

		}
		else if(e.getSource()==code)
		{
			JTextArea x;
					x=(JTextArea)tref.get(ftab.getSelectedIndex());
			x.setText(jep.getText());
		}
		else if(e.getSource()==html)
		{
			ftab.setBorder(BorderFactory.createTitledBorder("HTML_Mode"));	
			run.setEnabled(false);
			compile.setEnabled(false);
			html.setEnabled(false);
			normal.setEnabled(true);
			code.setEnabled(true);
			jep.setText("");
			jep.setEditable(true);
			jep.setContentType("text/html");
			modeType="html";
			JTextArea x;
					x=(JTextArea)tref.get(ftab.getSelectedIndex());
					jep.setText(x.getText());
		}
		else if(e.getSource()==normal)
		{
			ftab.setBorder(BorderFactory.createTitledBorder("JAVA_Mode"));	
			run.setEnabled(true);
			jep.setEditable(false);
			html.setEnabled(true);
			compile.setEnabled(true);
			normal.setEnabled(false);
			code.setEnabled(false);
			modeType="normal";
			jep.setText("");
			jep.setContentType("text/plain");
		}	
		}
		
			
	}
	
// run method code	
	
	public void runMethod()
	{	
		String yp,zn;
		yp=(String)pref.get(ftab.getSelectedIndex());
		zn=(String)nref.get(ftab.getSelectedIndex());
					
		
		tempc="";
		String name="";	
				 tempr="";	
		
		char arr[];
		arr=zn.toCharArray();
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]=='.')
			{
				break;
			}
			else
			{
				name=name+arr[i];
			}
		}
			
		
		String aa[]=new String[] {"cmd", "/c","start","cmd","/k","java "+name};
		
		File file=new File(yp);
		
		
		RunProgram thread=new RunProgram(aa,file);
		thread.start();
	
		
	}	
// compile method code		
	public void compile(FileMenuOperation fm)
	{
		
			saveMethod(fm);
		
			String sad;
		sad=(String)sref.get(ftab.getSelectedIndex());
		if(sad.equals("save"))	

				{
		
		String yp,zn;
		yp=(String)pref.get(ftab.getSelectedIndex());
		zn=(String)nref.get(ftab.getSelectedIndex());
				
		
		
					
		 tempc="";	
				
		String aa[]=new String[] {"cmd", "/c","javac "+zn};
		
		File file=new File(yp);
try
{ 
Runtime rt =Runtime.getRuntime();
Process proc = rt.exec(aa,null,file);
InputStream stderr =proc.getErrorStream();
InputStreamReader isr =new InputStreamReader(stderr);
BufferedReader br =new BufferedReader(isr);
String line =null;
while ( (line = br.readLine()) !=null)
{

tempc=tempc+line+"\n";
}
} catch (Throwable t)
{
t.printStackTrace();
}
		jep.setText(tempc+"\nProcess completed.");
	if(tempc.indexOf("invalid")>=0||tempc.indexOf("error")>=0)
	{
		jar.setEnabled(false);
	}
	else
	{		
		jar.setEnabled(true);
	}	
		
		}	
	}	
// jar file method code
	public void jar(FileMenuOperation fm)
	{
		String yp,zn;
		yp=(String)pref.get(ftab.getSelectedIndex());
		zn=(String)nref.get(ftab.getSelectedIndex());
		zn=zn.substring(0,zn.indexOf(".java"));
		File file=new File(yp);
		File [] f;
		String cl="",co="";
		f=file.listFiles();
		for(int i=0;i<f.length;i++)
		{
			cl=f[i].getName();
			if(cl.indexOf(".class")>=0)
			co=co+" "+cl.substring(0,cl.indexOf(".class"))+".class";
		}
		
		String aa[]=new String[] {"cmd", "/c","jar -cfe "+zn+".jar "+zn+co};
		try
		{ 
		Runtime rt =Runtime.getRuntime();
		Process proc = rt.exec(aa,null,file);
		JOptionPane.showMessageDialog(null,"Jar has been successfully generated","Result",JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Throwable t)
		{
		t.printStackTrace();
		}		
		
		jar.setEnabled(false);
			

	}

}

public class NotePadWithJavaIDE
{	

public static void main (String[] args){
	EditerDemo d=new EditerDemo();
		



}
} 