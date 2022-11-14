package platform.resource.main;

import platform.resource.main.vo.HandleResource;
import platform.resource.main.vo.HandleResourceInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class WindowResourse extends JFrame implements ActionListener {

	public String checkBox = "";
	public String resourcePath = "";
	String message = "";
	private HandleResourceInterface handleResource;

	JPanel PannelCheck, PannelFile, PannelOut;
	JButton scan, upload,uploadKnowledge, before, done, undo;
	JLabel jlb1, jlb2;
	TextField textField;
	JRadioButton RadioButtonTextBook, RadioButtonTextBookCatalog,
			RadioButtonTextBookResource;
	JFileChooser fc = new JFileChooser();
	TextArea area;
	JTextArea outText;
	public static String BEFORE_TEXT = "已经存在";
	public static String DONE_TEXT =  "本次处理成功的";
	public static String UNDO_TEXT =  "本次处理失败的";
	public static String PROCESS_TEXT = "上传中";
	public static String START_TEXT = "开始上传";
	public WindowResourse(HandleResourceInterface displayResource) {
		this.handleResource = displayResource;

		PannelCheck = new JPanel();
		PannelFile = new JPanel();
		PannelOut = new JPanel();
		//RadioButtonTextBook = new JRadioButton(HandleResource.FIRST, true);
		//RadioButtonTextBookCatalog = new JRadioButton(HandleResource.SECOND);
		RadioButtonTextBookResource = new JRadioButton(HandleResource.THIRD, true);

		textField = new TextField(25);
		PannelFile.add(textField);
		scan = new JButton("浏览");
		upload = new JButton(WindowResourse.START_TEXT);
		PannelFile.add(scan);
		PannelFile.add(upload);
		scan.addActionListener(this);
		upload.addActionListener(this);

		uploadKnowledge = new JButton(WindowResourse.START_TEXT+"知识点");
		PannelFile.add(uploadKnowledge);
		uploadKnowledge.addActionListener(this);

		before = new JButton(WindowResourse.BEFORE_TEXT);
		done = new JButton(WindowResourse.DONE_TEXT);
		undo = new JButton(WindowResourse.UNDO_TEXT);

		PannelOut.add(before);
		PannelOut.add(done);
		PannelOut.add(undo);

		before.addActionListener(this);
		done.addActionListener(this);
		undo.addActionListener(this);

		ButtonGroup radioGroup = new ButtonGroup();
		//radioGroup.add(RadioButtonTextBook);
		//radioGroup.add(RadioButtonTextBookCatalog);
		radioGroup.add(RadioButtonTextBookResource);

		this.setLayout(new GridLayout(4, 1));

		//PannelCheck.add(RadioButtonTextBook);
		//PannelCheck.add(RadioButtonTextBookCatalog);
		PannelCheck.add(RadioButtonTextBookResource);
		this.add(PannelCheck);
		this.add(PannelFile);
		this.add(PannelOut);

		outText = new JTextArea(22, 9);
		outText.append("");
		outText.setLineWrap(true);
		JScrollPane outTextScroll = new JScrollPane(outText);
		outTextScroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outTextScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(outTextScroll);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == scan) {
			int intRetVal = fc.showOpenDialog(this);
			if (intRetVal == JFileChooser.DIRECTORIES_ONLY) {
				textField.setText(fc.getCurrentDirectory().getPath());
			}
		}
		if (e.getSource() == upload) {
			String s1 = textField.getText();
			resourcePath = s1;
			/*if (RadioButtonTextBook.isSelected()) {
				checkBox = RadioButtonTextBook.getActionCommand();
			} else if (RadioButtonTextBookCatalog.isSelected()) {
				checkBox = RadioButtonTextBookCatalog.getActionCommand();
			} else */if (RadioButtonTextBookResource.isSelected()) {
				checkBox = RadioButtonTextBookResource.getActionCommand();
			}
			
			
			upload.setEnabled(false);
			
			handleResource.checkBox(checkBox);
			handleResource.resourcePath(resourcePath);
			message = handleResource.startUploadResource();
			
			upload.setEnabled(true);
		}

		//知识点上传
		if(e.getSource() == uploadKnowledge){
			uploadKnowledge.setEnabled(false);
			String s1 = textField.getText();
			resourcePath = s1;
			handleResource.resourcePath(resourcePath);
			message = handleResource.startUploadKnowledge();
			uploadKnowledge.setEnabled(true);
			outText.setText("");
			outText.setText(resourcePath+" 目录下的知识点已经上传完成。。。");
		}

		if (e.getSource() == before) {
			outText.setText("");
			outText.append(message);
			outText.append(handleResource.outBeforeDoneString());
		}
		if (e.getSource() == done) {
			outText.setText("");
			outText.append(message);
			outText.append(handleResource.outNowDoneString());
			
		}
		if (e.getSource() == undo) {
			outText.setText("");
			outText.append(message);
			outText.append(handleResource.outNowUndoString());
			
		}
		ifjb(e, before);
		ifjb(e, done);
		ifjb(e, undo);
	}

	private void ifjb(ActionEvent e, JButton jb) {
		if (e.getSource() == jb) {
			JFrame f = new JFrame();
			f.setSize(400, 400);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			String extensionName = getExtensionName(textField.getText());
			if ("txt".equals(extensionName)) {
				f.setTitle(jb.getActionCommand());
				area = new TextArea();
				String text = readTxt(textField.getText());
				area.setText(text);
				f.add(area);
				f.setVisible(true);
			}
		}
	}

	/**
	 * @Description
	 * @param filename
	 * @return
	 * @throws
	 */
	private String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * @Description
	 * @param pathַ
	 * @return
	 * @throws
	 */
	private String readTxt(String path) {
		if (path == null || "".equals(path)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		File file = new File(path);
		InputStreamReader read = null;
		BufferedReader reader = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), "gb2312");
			reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return sb.toString();
	}

}
