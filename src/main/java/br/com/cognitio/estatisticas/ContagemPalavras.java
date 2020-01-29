package br.com.cognitio.estatisticas;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContagemPalavras implements Iterable<ContagemPalavras.Contagem> {
	
	public static final class Contagem{
		private final String palavra;
		private final int quantidade;
		
		public Contagem(String palavra, int quantidade) {
			this.palavra = palavra;
			this.quantidade = quantidade;
		}
		
		public String GetPalavra() {
			return palavra;
		}
		
		public int getQuantidade() {
			return quantidade;
		}
	}
	
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

	@Override
	public Iterator<ContagemPalavras.Contagem> iterator() {
		
		Iterator<Map.Entry<String, Integer>> iterator = this.map.entrySet().iterator();
		
		return new Iterator<ContagemPalavras.Contagem>() {

			@Override
			public boolean hasNext() {
				
				return iterator.hasNext();
			}

			@Override
			public ContagemPalavras.Contagem next() {
				Map.Entry<String, Integer> entry = iterator.next();
				String palavra = entry.getKey();
				int quantidade = entry.getValue();
				return new ContagemPalavras.Contagem(palavra, quantidade);
			}
		};
	}

}
