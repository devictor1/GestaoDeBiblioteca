/* Curso Técnólogo de Análise e Desenvolvimento de Sistemas
* Nome do Desenvolvedor: Victor Fregni Gogorza
* Versão1.0
* 
* Projeto de Gestão de Biblioteca utilizando Java
*/

package gestaoDeBiblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class CapitalizarPrimeiraLetra {

	public static String capitalizarPrimeiraLetra(String nome) {
		if (nome == null || nome.isEmpty()) {
			return nome;
		} else {
			return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
		}
	}
}

class ComparadorPorTitulo implements Comparator<Livro> {
	@Override
	public int compare(Livro livro1, Livro livro2) {
		return livro1.getTitulo().compareTo(livro2.getTitulo());
	}
}

class ComparadorPorDisponibilidade implements Comparator<Livro> {
	@Override
	public int compare(Livro livro1, Livro livro2) {
		return Boolean.compare(livro1.isDisponivel(), livro2.isDisponivel());
	}
}

class ComparadorPorDataLancamento implements Comparator<Livro> {
	@Override
	public int compare(Livro livro1, Livro livro2) {
		LocalDate dataLancamento1 = livro1.getDataDeLancamento();
		LocalDate dataLancamento2 = livro2.getDataDeLancamento();

		return dataLancamento1.compareTo(dataLancamento2);
	}
}

class Livro {

	private String titulo;
	private String autor;
	private LocalDate dataDeLancamento;
	private int numPaginas;
	private boolean disponivel;

	public Livro(String titulo, String autor, LocalDate dataDeLancamento, int numPaginas, boolean disponivel) {

		this.titulo = titulo;
		this.autor = autor;
		this.dataDeLancamento = dataDeLancamento;
		this.numPaginas = numPaginas;
		this.disponivel = disponivel;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public LocalDate getDataDeLancamento() {
		return dataDeLancamento;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setTitulo(String setTitulo) {
		titulo = setTitulo;
	}

	public void setAutor(String setAutor) {
		autor = setAutor;
	}

	public void setDataDeLancamento(LocalDate setDataDeLancamento) {
		dataDeLancamento = setDataDeLancamento;
	}

	public void setNumPaginas(int setNumPaginas) {
		numPaginas = setNumPaginas;
	}

	public void emprestarLivro() {
		disponivel = false;
	}

	public void devolverLivro() {
		disponivel = true;
	}

	@Override

	public String toString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = dataDeLancamento.format(formatador);
		String tituloCapitalizado = CapitalizarPrimeiraLetra.capitalizarPrimeiraLetra(titulo);
		String autorCapitalizado = CapitalizarPrimeiraLetra.capitalizarPrimeiraLetra(autor);
		String disponibilidade = null;
		if (disponivel) {
			disponibilidade = "Está disponível!";
		} else {
			disponibilidade = "Não está disponível!";
		}

		return "Livro {" + "Titulo - " + tituloCapitalizado + " Autor - " + autorCapitalizado
				+ ", Data de Lançamento - " + dataFormatada + ", Número de Páginas - " + numPaginas
				+ ", Disponibilidade - " + disponibilidade + "}";
	}
}

public class GestaoDeBiblioteca {

	public static void main(String[] args) {

		ArrayList<Livro> livros = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int opcao = 0;

		while (true) {

			if (opcao == 0) {
				System.out.println("Menu:");
				System.out.println("1. Adicionar Livro");
				System.out.println("2. Mostrar todos os Livros");
				System.out.println("3. Editar um Livro");
				System.out.println("4. Pesquisar um Livro");
				System.out.println("5. Deletar um Livro");
				System.out.println("6. Sair");
				System.out.print("Escolha uma opção: ");

				opcao = sc.nextInt();
				System.out.println();

			} else if (opcao == 1) {
				System.out.print("Digite o Titulo do novo Livro: ");
				String titulo = sc.next();

				System.out.print("Digite o Autor do novo Livro:");
				String autor = sc.next();

				System.out.print("Digite o Ano em que o Livro foi lançado: ");
				int ano = sc.nextInt();
				System.out.print("Digite o Mês: ");
				int mes = sc.nextInt();
				System.out.print("Digite o Dia: ");
				int dia = sc.nextInt();
				LocalDate dataDeLancamento = LocalDate.of(ano, mes, dia);

				System.out.print("Digite o Número de páginas do novo livro: ");
				int numPaginas = sc.nextInt();
				boolean disponivel = true;

				Livro livro = new Livro(titulo, autor, dataDeLancamento, numPaginas, disponivel);
				livros.add(livro);
				System.out.println("Livro adicionado com sucesso!");
				System.out.println();

			} else if (opcao == 2) {
				if (livros.isEmpty()) {
					System.out.println("Ainda não há nenhum livro registrado!");
					System.out.println("Voltando ao menu...");
					System.out.println("");
					opcao = 0;
				} else {
					System.out.println("Como deseja ordená-los?");
					System.out.println("1. Ordem Alfabética");
					System.out.println("2. Livros disponíveis");
					System.out.println("3. Livros indisponíveis");
					System.out.println("4. Data de Lançamento");
					System.out.println("5. Voltar");
					int opcaoOrdem = sc.nextInt();
					if (opcaoOrdem == 1) {
						Collections.sort(livros, new ComparadorPorTitulo());
						System.out.println("Livros em ordem alfabética:");
						for (Livro livro : livros) {
							System.out.println(livro);
						}
						System.out.println();
					} else if (opcaoOrdem == 2) {
						Collections.sort(livros, new ComparadorPorDisponibilidade());
						System.out.println("Livros disponíveis:");
						for (Livro livro : livros) {
							if (livro.isDisponivel())
								System.out.println(livro);
						}
						System.out.println();
					} else if (opcaoOrdem == 3) {
						Collections.sort(livros, new ComparadorPorDisponibilidade());
						System.out.println("Livros indisponíveis:");
						for (Livro livro : livros) {
							if (!livro.isDisponivel())
								System.out.println(livro);
						}
						System.out.println();
					} else if (opcaoOrdem == 4) {
						Collections.sort(livros, new ComparadorPorDataLancamento());
						System.out.println("Livros por ordem de Lançamento:");
						for (Livro livro : livros) {
							System.out.println(livro);
						}
						System.out.println();
					} else if (opcaoOrdem == 5) {
						opcao = 0;

					} else {
						System.out.println("Opção inválida. Tente novamente");
						opcao = 2;
					}
				}
			} else if (opcao == 3) {
				System.out.println("Digite o titulo do livro que deseja editar");
				String tituloLivroEditavel = sc.next();
				Livro livroParaEditar = null;

				if (livros.isEmpty()) {
					System.out.println("Ainda não há nenhum livro registrado!");
					System.out.println("Voltando ao menu...");
					System.out.println("");
					opcao = 0;
				} else {
					for (Livro livro : livros) {
						if (livro.getTitulo().equals(tituloLivroEditavel)) {
							livroParaEditar = livro;
							break;
						}
					}

					if (livroParaEditar == null) {
						System.out.println("Não há nenhum livro com esse titulo!");
						System.out.println("Voltando ao menu...");
						System.out.println("");
						opcao = 0;
						;
					} else {
						System.out.println("E o quê você deseja editar?");
						System.out.println("1. Editar Titulo");
						System.out.println("2. Editar Autor");
						System.out.println("3. Editar Data De Lançamento");
						System.out.println("4. Editar Número de Páginas");
						System.out.println("5. Voltar ao menu");
						int opcaoEditar = sc.nextInt();
						System.out.println();
						if (opcaoEditar == 1) {
							System.out.println("Digite o Titulo atualizado do livro escolhido");
							livroParaEditar.setTitulo(sc.next());
							System.out.println("Livro editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 2) {
							System.out.println("Digite o Autor atualizado do livro escolhido");
							livroParaEditar.setAutor(sc.next());
							System.out.println("Livro editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 3) {
							System.out.print("Digite o Ano atualizado em que o Livro foi lançado: ");
							int ano = sc.nextInt();
							System.out.print("Digite o Mês atualizado: ");
							int mes = sc.nextInt();
							System.out.print("Digite o Dia atualizado: ");
							int dia = sc.nextInt();
							LocalDate dataDeLancamento = LocalDate.of(ano, mes, dia);
							livroParaEditar.setDataDeLancamento(dataDeLancamento);
							System.out.println("Livro editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 4) {
							System.out.println("Digite a Data de Nascimento atualizada do livro escolhido");
							livroParaEditar.setNumPaginas(sc.nextInt());
							System.out.println("Livro editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 5) {
							System.out.println("Voltando ao menu...");
							System.out.println("");
							opcao = 0;
						} else {
							System.out.println("Opção inválida. Tente novamente.");
							System.out.println();
						}
					}
				}
			} else if (opcao == 4) {
				System.out.println("Digite o titulo do livro que deseja encontrar");
				String tituloLivro = sc.next();
				Livro encontrarLivro = null;

				if (livros.isEmpty()) {
					System.out.println("Ainda não há nenhum livro registrado!");
					System.out.println("Voltando ao menu...");
					System.out.println("");
					opcao = 0;
				} else {
					for (Livro livro : livros) {
						if (livro.getTitulo().equals(tituloLivro)) {
							encontrarLivro = livro;
							break;
						}
					}
				}

				if (encontrarLivro == null) {
					System.out.println("Não há nenhum livro com esse titulo!");
					System.out.println("Voltando ao menu de pesquisa...");
					System.out.println("");
					opcao = 4;
				} else {
					System.out.println(encontrarLivro);

				}

			} else if (opcao == 5) {
				if (livros.isEmpty()) {
					System.out.println("Ainda não há nenhum livro registrado!");
					System.out.println("Voltando ao menu...");
					System.out.println("");
					opcao = 0;
				}
				System.out.println("Qual o titulo do livro que deseja excluir?");
				String removerLivro = sc.next();
				System.out.println("Você tem certeza que deseja excluir? Use S para sim e N para não");
				String resposta = sc.next();
				if (resposta.equals("s") || resposta.equals("S")) {

					for (Livro livro : livros) {
						if (livro.getTitulo().equalsIgnoreCase(removerLivro)) {
							livros.remove(livro);
							System.out.println("Livro removido com sucesso!");
							System.out.println();
							break;
						} else {
							System.out.println("Nenhum livro com esse titulo!");
							System.out.println("Voltando ao menu...");
							System.out.println("");
							opcao = 0
						}

					}
				} else {
					opcao = 0;
				}
			} else if (opcao == 6) {
				System.out.println("Saindo...");
				break;

			} else {
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println("Voltando ao menu...");
				opcao = 0;
			}

		}
		sc.close();
	}

}