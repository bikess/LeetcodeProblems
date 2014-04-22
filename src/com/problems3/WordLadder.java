package com.problems3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	/**
	 * @param args
	 */
public class Solution{
	public int ladderLength(String start, String end, HashSet<String> dict) {
		Set<String> set = new HashSet<>();
		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		int distance = 1;
		int count = 1;
		set.add(start);
		while(count>0){
			while(count>0){
				char[] curr = queue.poll().toCharArray();
//				这一层遍历是，字符串中改变某一个字符，从第0个字符到第n-1个字符循环遍历
				for(int i=0;i<curr.length;i++){
					char tmp = curr[i];
//					主要看如何遍历26个英文字母的方法
					for(char c ='a';c<='z';c++){
						if(c==tmp)
							continue;
						curr[i] = c;
						String str = new String(curr);
						if(str.equals(end))
							return distance+1;
						if(dict.contains(str)&&!set.contains(str)){
//							这样把词典中有的字符串，且与该比较的字符串相差一个字符的所有字符串加入的队列中。
							queue.offer(str);
							set.add(str);
						}
					}
					curr[i] = tmp;
				}
				count--;
			}
			distance++;
			count = count+queue.size();
		}
		return 0;
	        
	}
}
public int ladderLength2(String start, String end, HashSet<String> dict) {
	if(start == null||end ==null||start.equals(end)){
		return 0;
	}
	Queue<String> queue = new LinkedList<String>();
	HashMap<String, Integer> dist = new HashMap<>();
	
	queue.add(start);
	dist.put(start, 1);
	/**
	 * offer    添加一个元素并返回true       如果队列已满，则返回false
	 * poll     移除并返问队列头部的元素               如果队列为空，则返回null
	 */
	while(!queue.isEmpty()){
		String head = queue.poll();
		
		int headDist = dist.get(head);
		//从每一个位置开始替换成a-z；
		for(int i=0;i<head.length();i++){
			for(char j='a';j<'z';j++){
				if(head.charAt(i)==j)
					continue;
				StringBuffer sb = new StringBuffer();
//				把字符串的i位置设置为字符j
				sb.setCharAt(i, j);
				if(sb.toString().equals(end))
					return headDist+1;
				if(dict.contains(sb.toString())&&!dist.containsKey(sb.toString())){
					queue.add(sb.toString());
					dist.put(sb.toString(), headDist+1);
				}
			}
		}
	}
	return 0;

}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String start = "hit";
		String end = "cog";
		HashSet<String> dict = new HashSet<>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		ArrayList<ArrayList<String>> result = findLadders3(start, end, dict);
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i).toString()+"\tnimei");
	}

	/***
	 * 题目扩展，返回的不是最短路径的长度，而是返回最短路径的序列，可能有多个啊
	 */
	
	/**
	 * 
	 * 思路1 ：采取的是深度优先遍历，从start开始，对每个字母位置从‘a’到‘z’尝试替换，若
	 * 替换字母后的单词在字典中，将其加入路径，然后以新单词为起点进行递归调用，否则继续
	 * 循环。每一层递归函数终止的条件是end出现或者单词长度*26个字母循环完毕。end出现的时候表示
	 * 找到一个序列，对比当前最短序列做相应的更新即可。
	 * 处理过程注意有几点：
	 * （1）不能相同的字母替换
	 * （2）转换序列不能出现重复的单词
	 * （3）处理过程中若发现当前处理队列长度已经超过了当前的最短序列长度，则终止
	 */
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		ArrayList<String> entry = new ArrayList<>();
		entry.add(start);
		find(start,end,dict,0,result,entry);
		
		return result;
		
	}
	public void find(String start, String end, HashSet<String>dict,int positionTochange,ArrayList<ArrayList<String>> 
	result, ArrayList<String> entry)
	{
		HashSet<String> used = new HashSet<>();
		//若长度已经等于当前处理结果中的长度，再找出来肯定就超过了，终止处理
		if(!result.isEmpty()&&entry.size()==result.get(0).size()){
			return;
		}
		for(int i = positionTochange;i<start.length();i++){
			char beforeChange = ' ';
			char[] curr = start.toCharArray();
			for(char j = 'a';j<='z';j++)
			{
				//防止同字母被替换
				if(curr[i]==j){
					continue;
				}
				beforeChange = curr[i];
				curr[i] = (char) j;
				//若单词出现已经用过的情况
				if(!used.isEmpty()&&used.contains(curr.toString())){
					curr[i] = beforeChange;
					continue;
				}
				start = curr.toString();
				
//				这是深度优先递归调用结束的条件
				if(start == end){
					entry.add(start);
					
					if(!result.isEmpty()){
						if(entry.size()<result.get(0).size()){
							result.clear();
							result.add(entry);
						}else if(entry.size()==result.get(0).size()){
							result.add(entry);
						}
					}else{
						result.add(entry);
					}
//					完成一个序列，把前面加入的end弹出来
					entry.remove(entry.size()-1);
					return;
				}
				if(dict.contains(start)){
					entry.add(start);
					used.add(start);
					find(start, end, dict, 0, result, entry);
					used.clear();
					entry.remove(entry.size()-1);
					
					if(!entry.isEmpty()){
						start = entry.get(0);
					}else{
						curr[i] = beforeChange;
						start = curr.toString();
					}
				}else{
					curr[i] = beforeChange;
					start = curr.toString();
				}
			}
		}
		return;
	}	
	
	/***
	 * 
	 * 思路2 ：采用广度优先搜索算法，也就是利用贪心算法来做，
	 * 思路：从start串出发，找出作一次变换可以得到的string串的集合S1，
	 * 若集合S1中包含end串，那么搜索结束，否则，搜索两步之内能到达的串的集合S2，同样
	 * 判断两步之内能到达的串集合中是否含有end串，依次类推，最终找到最短路径。
	 * 最终算法描述如下：
	 * (1)将字典dict所有字符串分为左右两侧，一侧为leftside = start；
	 * 一侧为rightside=（dict-start），当前距离start最远的节点。
	 * （2）计算nextStep，也就是i+1步可以到达的字符串。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<String>> findLadders2(String start, String end, HashSet<String> dict) {
		
		Map<String,ArrayList<String>> path = new HashMap<String, ArrayList<String>>();
		HashSet<String> leftside = new HashSet<String>();
		HashSet<String> rightside = dict;
		rightside.add(start);
		rightside.add(end);
		
		leftside.add(start);
		rightside.remove(start);
		
		HashSet<String> curStep = new HashSet<String>();
		HashSet<String> nextStep= new HashSet<String>();
		curStep.add(start);
//		循环结束的条件是当前此步的循环中包含end字符串，而且词典的右半部分不为空
//		当当前步中不含end字符串或者词典右半部已经没有了，则循环结束
		while(!curStep.contains(end)&&!rightside.isEmpty()){
			Iterator<String> iter_cur = curStep.iterator();
			Iterator<String> iter_right = rightside.iterator();
//			第一层循环是所有的当前i步的所有单词，开始第0步时候，cur中只包含start
//			第二层循环针对当前i步的单词，判断是否存在下一步的单词在right当中
			while(iter_cur.hasNext()){
				String a = null; 
				a=iter_cur.next();
//				System.out.println(a);
				while(iter_right.hasNext()){
					String b = null;
					b = iter_right.next();
//					System.out.println(b);
					if(isCvtable(a,b)){
						if(!path.isEmpty()&&path.containsKey(a)){
							path.get(a).add(b);
						}else{
							ArrayList<String> empty = new ArrayList<String>();
							empty.add(b);
							path.put(a, empty);
						}
						nextStep.add(b);
					}
				}
//				易错，循环完一遍，iter_right 指向末尾了，必须在从新指向
				iter_right = rightside.iterator();
			}

//			若下一步的字符串没有，则停止循环
			if(nextStep.isEmpty())
				break;
//			从词典右半部分中擦去已经存在与curStep中的单词
			Iterator<String> iter_next = nextStep.iterator();
			while(iter_next.hasNext()){
				rightside.remove(iter_next.next());
			}
//			System.out.println(nextStep.toString());	
			//这里非常容易错，一定注意java在赋值的时候是进行对象引用的赋值，这里若采取curStep = nextStep,是把nextStep对象的引用赋给了
//			另一个对象，此时这个对象指向这个对象的引用，会随着这个对象的变化而变化，若只是想把这个对象的持有的值赋值给这个对象，一定要利用clone方法！！！			
			curStep = (HashSet<String>) nextStep.clone();
//			System.out.println(curStep.toString());
			nextStep.clear();
		
		}
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		for(Map.Entry<String, ArrayList<String>> entry : path.entrySet()){
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
//			System.out.println(key+value.toString());
		}
		System.out.println(path.toString());
		if(curStep.contains(end)){
			output(path,start,end,result,temp);
		}

		return result;
		
	}
//	递归输出包含end路径的值
	private static void output(Map<String, ArrayList<String>> path, String start,
			String end, ArrayList<ArrayList<String>> result,
			ArrayList<String> temp) {
		// TODO Auto-generated method stub
		temp.add(start);
		System.out.println(temp.toString());
		System.out.println(start+"\t"+end);
		System.out.println(start.hashCode()+"\t"+end.hashCode());
		if(start.equals(end)){
			result.add(temp);
			System.out.println("nimaiaf");
			System.out.println(result.toString());
			return;
		}
//		System.out.println(result.toString());
		if(path.containsKey(start)){
		ArrayList<String> m = path.get(start);
		Iterator<String> iterator = m.iterator();
		while(iterator.hasNext()){
			String a = iterator.next();
			output(path, a, end, result, temp);
			temp.remove(temp.size()-1);
		}
		}else{
			return;
		}
		
	}
	//	判断两个单词是否相差只有一个字母不一样
	private static boolean isCvtable(String str1,
			String str2) {
		// TODO Auto-generated method stub
    if(str1.length()!=str2.length()){return false;}
        
    int count=0;
    for(int i = 0;i<str1.length();++i)
    {
       if(str1.charAt(i)!=str2.charAt(i))count++;
            if(count>1)return false;
    }
        
    return true;
	}

	/***
	 * 上面的思路太耗时，主要是从curStep求解nextStep的过程太耗时，从curStep求解nextStep的时候不用一个一个的与所有字典中的词进行判断，
	 * 可以直接改变单词中的某个字母（从26个英文字母中依次遍历），看改变后的单词是否存在与这个词典中
	 */
	
	public  static ArrayList<ArrayList<String>> findLadders3(String start, String end, HashSet<String> dict) {
		
		Map<String,ArrayList<String>> path = new HashMap<String, ArrayList<String>>();
		HashSet<String> leftside = new HashSet<String>();
		HashSet<String> rightside = new HashSet<String>();
		rightside = dict;
		rightside.add(start);
		rightside.add(end);
		HashSet<String> curstep = new HashSet<String>();
		HashSet<String> nextstep = new HashSet<String>();
		curstep.add(start);
		leftside.add(start);
		rightside.remove(start);
		
		while(!rightside.isEmpty()&&!curstep.contains(end)){
			Iterator<String> iter_cur = curstep.iterator();
//			System.out.println(curstep.toString());
			while(iter_cur.hasNext()){
				String cur =iter_cur.next();
//				System.out.println(cur);
				char[] curr = cur.toCharArray();
//				java 转String 的方法 String.valueOf,容易出错，你妹的
//				System.out.println(curr.toString());
				for(int i=0;i<cur.length();i++){
					char before = curr[i];
					for(char j='a';j<='z';j++)
					{
//						System.out.println(j);
						if(cur.charAt(i)==j)
							continue;
						curr[i] = j;
//						System.out.println(String.valueOf(curr));
						String compare = String.valueOf(curr);
						if(rightside.contains(compare)){
							nextstep.add(compare);
							if(!path.isEmpty()&&path.containsKey(cur)){
								path.get(cur).add(compare);
							}else{
								ArrayList<String> entry = new ArrayList<>();
								entry.add(compare);
								path.put(cur, entry);
							}
						}
					}
					curr[i] = before;
				}
			}
			if(nextstep.isEmpty())
				break;
			curstep = (HashSet<String>) nextstep.clone();
//			rightside.remove(nextstep);
//			从词典右半部分中擦去已经存在与curStep中的单词
			Iterator<String> iter_next = nextstep.iterator();
			while(iter_next.hasNext()){
				rightside.remove(iter_next.next());
			}
			nextstep.clear();
		}
		System.out.println(path.toString());
//		System.out.println(curstep.toString());
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> my = new ArrayList<>();
		my.add("nima");
		result.add(my);
		ArrayList<String> temp = new ArrayList<>();
		if(curstep.contains(end)){
//			System.out.println("nimei");
			System.out.println(start+end);
			output(path,start,end,result,temp);
		}
		return result;
	
	}
	
	
	
	
	
	
	
	
	
}
