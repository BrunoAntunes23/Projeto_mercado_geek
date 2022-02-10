package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.WeakHashMap;

import helper.Util;
import model.Produto;

public class Mercado {
	private static Scanner teclado=new Scanner(System.in);
	//criamos um conteiner array list do tipo produto
	private static ArrayList<Produto>produtos;
	private static Map<Produto, Integer>carrinho;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		produtos=new ArrayList<Produto>();
		carrinho=new WeakHashMap<Produto, Integer>();
		Mercado.menu();
		
		
		

	}
	private static void menu(){
		System.out.println("===============================");
		System.out.println("==========Bem-vindo============");
		System.out.println("===============================");
		
		System.out.println("Selecione uma opção baixo");
		System.out.println("1-Cadastrar Produto");
		System.out.println("2-Listar Produto");
		System.out.println("3-Comprar Produto");
		System.out.println("4-Visualizar carrinho");
		System.out.println("5-sair");
		int opc=0;
		try {
			opc=Integer.parseInt(Mercado.teclado.nextLine());
			
		}catch(InputMismatchException e) {
			Mercado.menu();
		}catch(NumberFormatException f) {
			Mercado.menu();
		}
		switch (opc) {
		case 1:
			Mercado.cadastrarP();
			break;
		case 2:
			Mercado.listarP();
			break;
		case 3:
			Mercado.comprarP();
			break;
		case 4:
			Mercado.visualizarC();
			break;
		case 5:
			System.out.println("volte sempre");
			Util.pausar(2);
			System.exit(0);
		default:
			System.out.println("opção invalida");
			Util.pausar(3);
			Mercado.menu();
		break;
		
			

		}
		
		
		}
	
	private static void cadastrarP() {
		System.out.println("cadastrando produto");
		System.out.println("------------------- \n informe o nome do produto");
		String nome=Mercado.teclado.nextLine();
		System.out.println("informe o preço do produto");
		double preco=Mercado.teclado.nextDouble();
		Produto produto =new Produto(nome,preco);
		Mercado.produtos.add(produto);
		System.out.println("o produto : "+produto.getNome()+" foi adicionado com sucesso");
		Util.pausar(2);
		Mercado.menu();
		
		
		
	}
	private static void listarP() {
		System.out.println("listando produtos");
		if(Mercado.produtos.size()>0) {
		System.out.println("lista de produtos \n");	
		for (Produto p:Mercado.produtos) {
			System.out.println(p+"\n");
		}
		}else{
			System.out.println("não existe produtos cadastrados no momento");
		}
		Util.pausar(5);
		Mercado.menu();
		
	}
	private static void comprarP() {
		if(Mercado.produtos.size()>0) {
			
			System.out.println("informe o codigo do produto que deseja comprar");
			System.out.println("produtos disponiveis");
			for(Produto p:Mercado.produtos) {
				System.out.println(p+"\n ------------------");
				
			}
			int codigo=Integer.parseInt(Mercado.teclado.nextLine());
			boolean tem=false;
			for(Produto p:Mercado.produtos) {
				if(p.getCodigo()==codigo) {
					int quant=0;
					try {
						quant=Mercado.carrinho.get(p);
						Mercado.carrinho.put(p, quant+1);
						System.out.println("O produto"+p.getNome()+" foi adicionado ao carrinho");
						tem=true;
						if(tem) {
							System.out.println("Deseja adicionar outros produtos? \n informe 1-sim 2-não");
							int op=Integer.parseInt(Mercado.teclado.nextLine());
							if(op==1){
								Mercado.comprarP();
							}else if(op==2){
								System.out.println("Por favor aguarde enqunato fechamos o pedido");
								//TO DO
								Mercado.FecharPedido();}
								
							}else {
								System.out.println("não foi possivel encontrar o produto com o código: "+codigo);
								Util.pausar(3);
								Mercado.menu();}
						
					}catch(NullPointerException e){
						Mercado.carrinho.put(p,1);
						
					}
					
					}
				}
				
			}
			else {
			System.out.println("não foi possivel achar produtos cadastratos tente novamente");
			Util.pausar(5);
			Mercado.menu();
		}
		
	}
	private static void visualizarC() {
		System.out.println("preparando a visualização dos produtos");
		if(Mercado.carrinho.size()>0) {
			System.out.println("produtos no carrinho");
			for(Produto p:  Mercado.carrinho.keySet()) {
				System.out.println("Produto :\n"+p+"\nQuantidade :\n"+Mercado.carrinho.get(p));
			}
		}else {
			System.out.println("não existe produtos ainda no carrinho");
		}Util.pausar(3);
		Mercado.menu();
		
	}
	private static void FecharPedido() {
		Double  valorTot=0.0;
		System.out.println("produtos do carrinho \n ----------------------");
		for(Produto p:Mercado.carrinho.keySet()) {
			int quant=Mercado.carrinho.get(p);
			valorTot+=p.getPreco()*quant;
			System.out.println(p+"\n Quantidade :"+quant+"\n----------------");
			System.out.println("sua fatura é"+Util.doubleParaString(valorTot));
			Mercado.carrinho.clear();
			System.out.println("Obrigado pela preferencia");
			Util.pausar(5);
			Mercado.menu();
		
		}
	}
	}
	



