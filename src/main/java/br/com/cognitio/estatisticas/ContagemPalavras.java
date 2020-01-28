package br.com.cognitio.estatisticas;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContagemPalavras {
	
	private Map<String, Integer> map = new TreeMap<>();
	
	public Set<Map.Entry<String, Integer>> entrySet(){
		return map.entrySet();
	}
	
	public void adicionaPalavras(String palavra) {
		Integer contagem = map.get (palavra);
		
		if(contagem != null) {
			contagem++;
		}else {
			contagem = 1;
		}
		
		map.put(palavra, contagem);
	}

}
