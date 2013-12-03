package zeitgeist.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import zeitgeist.common.zei_FileListFilter;
import zeitgeist.common.zei_Quest;

public class zei_QuestHandler {
	public static void findQuestFiles() {
		System.out.println("The method has been called.");
		String dir = zdir.getName();
		File f1 = new File(dir);
		zei_FileListFilter filter = new zei_FileListFilter("Quest_", "txt");
		File[] ls = zdir.listFiles(filter);
		if (ls == null) {
			return;
		}
		for (int i1 = 0; i1 < ls.length; i1++) {
			if (!(ls[i1].getName().startsWith("Quest_"))
					|| !(ls[i1].getName().endsWith(".txt"))) {
				continue;
			}
			questList.add(ls[i1]);
		}
		Iterator<File> it = questList.iterator();
		while (it.hasNext()) {
			String s = it.next().getName();
			String s1 = zeiQuestData.readEntireFile(s);
			String s2 = s.substring("Quest_".length());
			String[] s3 = zeiQuestData.readEntireFileArray(s);
			Entity entity = null;
			Entity entity1 = null;
			Entity entity2 = null;
			int i = s3[1].equals("NONE") ? 0 : Integer.parseInt(s3[1]);
			int i1 = s3[4].equals("NONE") ? 0 : Integer.parseInt(s3[4]);
			int i2 = s3[2].equals("NONE") ? 0 : Integer.parseInt(s3[2]);
			zei_Quest addedQuest = new zei_Quest(s2, i, entity1, i2,
					Integer.parseInt(s3[3]), i1, s3[6], entity2, s3[8], entity,
					Integer.parseInt(s3[8]));
			System.out.println(addedQuest.name);
			questMap.put(s3[0], addedQuest);
			System.out.println("File " + s + " contains: " + s1);
		}
	}

	public static boolean getIsQuestGiver(String QuestGiver) {
		return questMap.containsKey(QuestGiver);
	}

	public static boolean QuestMapExists() {
		return questMap != null;
	}

	public static boolean QuestMapIsEmpty() {
		return questMap.isEmpty();
	}

	public static zei_Quest getQuest(String questGiver) {
		return questMap.get(questGiver);
	}

	private static List<File> questList = new ArrayList<File>();
	public static Map<String, zei_Quest> questMap = new HashMap<String, zei_Quest>();

	static class zeiQuestData {
		private static String readLineFromFile(String s) {
			String s1 = null;
			try {
				File file = new File(zdir, s);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				s1 = br.readLine();
			} catch (Exception e) {
			}
			return s1;
		}

		private static String readLineFromFile(String s, int skipLines) {
			String s1 = null;
			try {
				File file = new File(zdir, s);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				if (skipLines >= 0) {
					for (int i = 0; i < skipLines; i++) {
						br.readLine();
					}
				}
				s1 = br.readLine();
				br.close();
			} catch (Exception e) {
			}
			return s1;
		}

		private static String readEntireFile(String s) {
			String s1 = null;
			StringBuilder sb = new StringBuilder();
			try {
				File file = new File(zdir, s);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while (true) {
					s1 = br.readLine();
					if (s1 == null) {
						break;
					}
					sb.append(s1);
				}
				br.close();
			} catch (Exception e) {
			}
			return sb.toString();
		}

		private static String[] readEntireFileArray(String s) {
			String s1 = null;
			String[] sb = new String[15];
			try {
				File file = new File(zdir, s);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				int i = 0;
				while (true) {
					s1 = br.readLine();
					if (s1 == null) {
						break;
					}
					sb[i] = s1;
					i++;
				}
				br.close();
			} catch (Exception e) {
			}
			return sb;
		}

		private static File dir = Minecraft.getMinecraft().mcDataDir;
		private static File zdir = new File(dir, "ZeitGeist");
	}

	private static File dir = Minecraft.getMinecraft().mcDataDir;
	private static File zdir = new File(dir, "ZeitGeist");
}