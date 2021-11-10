package robo;

/*********************************************************************/
/** ACH 2002 - Introducao à Análise de Algoritmos                   **/
/** EACH-USP - Segundo Semestre de 2018                             **/
/**                                                                 **/
/** <Maraiza Adami Pereira > 		                             **/
/**                                                                 **/
/*********************************************************************/

/**
	Classe que implementa os movimentos do robô.
*/
import java.util.ArrayList;
import java.util.List;

public class Robo implements IRobo {
 /** Coordenada x de início da busca */
 private static final int x = ISala.X_INICIO_ARM;

 /** Coordenada y de início da busca */
 private static final int y = ISala.Y_FIM_ARM + 1;

 /** Mensageiro do robô **/
 public Mensageiro mensageiro;

 /** Construtor da classe sala*/
 Sala sala = new Sala();



 /** Construtor padrão para o robô **/
 public Robo() {
  this.mensageiro = new Mensageiro();
	
     novaBusca();

 }

 public void adicionaBloco(int x, int y) {

  if ((x >= 0 & x <= 9) && (y >= 0 & y <= 9)) {

   boolean i = sala.marcaPosicaoBusca(x, y, sala.BLOCO_PRESENTE);

  // System.out.println("Bloco adicionado");
  }
 }


 // input do usuário para obstaculos
 public void adicionaObstaculo(int x, int y) {
  if ((x >= 0 & x <= 9) && (y >= 0 & y <= 9)) {
   sala.marcaPosicaoBusca(x, y, sala.OBSTACULO_PRESENTE);
   sala.Tabuleiro[x][y] = sala.OBSTACULO_PRESENTE;

  // System.out.println("Obstaculo adicionado");
  }
 }

 //PREPARA A SALA PARA UMA NOVA BUSCA REMOVENDO MARCAS MARCA_PRESENTE
 public void novaBusca() {
  for (int x = 0; x <= sala.TAMANHO_SALA - 1; x++) {
   for (int y = 0; y <= sala.TAMANHO_SALA - 1; y++) {
    sala.marcaPosicaoBusca(x, y, sala.POSICAO_VAZIA);
    // remove o marcador de uma determinada posição da area de busca( exceto obstaculos)
   }
  }
 }

 /** Procura uma posição vazia dentro da área de armazenagem, armazenando lá um bloco O armazenamento do bloco é feito colocando-se um ISala.BLOCO_PRESENTE nessa posição. Despreze os movimentos do robô dentro da área de armazenagem. Após fazer a marcação, o programa envia mensagem ao usuário avisando (via Mensageiro). Returns:true se efetivamente armazenou algo, false se não pode armazenar (pela área estar cheia, por exemplo).
  */
 public boolean guardaBloco() {
    if (sala.Tabuleiro[0][0] != sala.BLOCO_PRESENTE){
        this.mensageiro.mensagem(Mensageiro.ARMAZENAGEM, 0, 0);
        sala.Tabuleiro[0][0] = sala.BLOCO_PRESENTE;
    }
    else if (sala.Tabuleiro[1][0] != sala.BLOCO_PRESENTE){
        this.mensageiro.mensagem(Mensageiro.ARMAZENAGEM, 1, 0);
        sala.Tabuleiro[1][0] = sala.BLOCO_PRESENTE;
    }
    else if (sala.Tabuleiro[0][1] != sala.BLOCO_PRESENTE){
        this.mensageiro.mensagem(Mensageiro.ARMAZENAGEM, 0, 1);
        sala.Tabuleiro[0][1] = sala.BLOCO_PRESENTE;
    }
    else if (sala.Tabuleiro[1][1] != sala.BLOCO_PRESENTE){
        this.mensageiro.mensagem(Mensageiro.ARMAZENAGEM, 1, 1);
        sala.Tabuleiro[1][1] = sala.BLOCO_PRESENTE;
        this.mensageiro.msgFim();
    }

  return true;
 }


 public boolean buscaBloco(int x, int y) { // Esse metodo procura UM bloco
  int x_inicial = x;
  int y_inicial = y;
  List < String > log = new ArrayList ();

  //zera todos as celulas que foram visitadas
  for (int x1 = 0; x1 <= sala.TAMANHO_SALA - 1; x1++) {
   for (int y1 = 2; y1 <= sala.TAMANHO_SALA - 1; y1++) {
    if (sala.Tabuleiro[x1][y1] == sala.MARCA_PRESENTE) {
     sala.Tabuleiro[x1][y1] = sala.POSICAO_VAZIA;
    }
   }
  }


  sala.posicaoBuscaValida(x, y);
	this.mensageiro.mensagem(Mensageiro.BUSCA, x, y);
  while (true) {
   //System.out.println(x+"|"+y);
   //System.out.println(log);

   //posição vazia
   if (sala.Tabuleiro[x][y] == sala.POSICAO_VAZIA) {
    sala.Tabuleiro[x][y] = sala.MARCA_PRESENTE;
    //adiciona posicao ao log
    String temp = x + "," + y;
    log.add(temp);
   }

   //bloco
   if (sala.Tabuleiro[x][y] == sala.BLOCO_PRESENTE) {
    this.mensageiro.mensagem(Mensageiro.CAPTURA, x, y);
    sala.Tabuleiro[x][y] = sala.MARCA_PRESENTE;
    //adiciona posicao ao log
    String temp = x + "," + y;
    log.add(temp);
    //System.out.println("achei");
    break;
   }

   //detecta obstaculos
   if ((y < sala.TAMANHO_SALA - 1) && (sala.Tabuleiro[x][y + 1] == sala.OBSTACULO_PRESENTE)) {
    this.mensageiro.mensagem(Mensageiro.OBSTACULO, x, y + 1);
   } else if ((x < sala.TAMANHO_SALA - 1) && (sala.Tabuleiro[x + 1][y] == sala.OBSTACULO_PRESENTE)) {
    this.mensageiro.mensagem(Mensageiro.OBSTACULO, x + 1, y);
   } else if ((y > 1) && (sala.Tabuleiro[x][y - 1] == sala.OBSTACULO_PRESENTE)) {
    this.mensageiro.mensagem(Mensageiro.OBSTACULO, x, y - 1);
   } else if ((x > 1) && (sala.Tabuleiro[x - 1][y] == sala.OBSTACULO_PRESENTE)) {
    this.mensageiro.mensagem(Mensageiro.OBSTACULO, x - 1, y);
   }



   if ((y < sala.TAMANHO_SALA - 1) && (sala.Tabuleiro[x][y + 1] == sala.POSICAO_VAZIA || sala.Tabuleiro[x][y + 1] == sala.BLOCO_PRESENTE) && (sala.posicaoBuscaValida​(x,y+1)==true)) {
    y += 1; //direita   	
    this.mensageiro.mensagem(Mensageiro.BUSCA, x, y);
   } else if ((x < sala.TAMANHO_SALA - 1) && (sala.Tabuleiro[x + 1][y] == sala.POSICAO_VAZIA || sala.Tabuleiro[x + 1][y] == sala.BLOCO_PRESENTE) && (sala.posicaoBuscaValida​(x+1,y)==true)) {
    x += 1; //CIMA      	
    this.mensageiro.mensagem(Mensageiro.BUSCA, x, y);
   } else if ((y >= 1) && (sala.Tabuleiro[x][y - 1] == sala.POSICAO_VAZIA || sala.Tabuleiro[x][y - 1] == sala.BLOCO_PRESENTE) && (sala.posicaoBuscaValida​(x,y-1)==true)) {
    y -= 1; //baixo     	
    this.mensageiro.mensagem(Mensageiro.BUSCA, x, y);
   } else if ((x >= 1) && (sala.Tabuleiro[x - 1][y] == sala.POSICAO_VAZIA || sala.Tabuleiro[x - 1][y] == sala.BLOCO_PRESENTE) && (sala.posicaoBuscaValida​(x-1,y)==true)) {
    x -= 1; //esquerda
    this.mensageiro.mensagem(Mensageiro.BUSCA, x, y);
   }

    // implementar fuga de beco e chegagem se eh area de armazenagem
    else {
   log.remove(log.size() - 1);
   String xy = log.get(log.size() - 1);
   String[] parts = xy.split(",");
   x = Integer.valueOf(parts[0]);
   y = Integer.valueOf(parts[1]);
   if (log.size() == 1) {
    //System.out.println("nao achei.fim.");
    this.mensageiro.msgNaoAchou();
    this.mensageiro.imprimeMensagens();
    System.exit(0);
   }
   this.mensageiro.mensagem(Mensageiro.BUSCA, x, y);
    }
  }

  //System.out.println("Log: " + log);
  while (true) {
   log.remove(log.size() - 1);
   String xy = log.get(log.size() - 1);
   String[] parts = xy.split(",");
   x = Integer.valueOf(parts[0]);
   y = Integer.valueOf(parts[1]);
   this.mensageiro.mensagem(Mensageiro.RETORNO, x, y);

   if (log.size() == 1) {
    guardaBloco();
    break;
   }
  }

  return true;
 }


 /** Esse médito chama o método anterior (buscaBloco) várias vezes para achar todos os blocos na seguencia
 executa a busca por todos os blocos espalhados pela sala */

 public void buscaBlocos() {
    int aux = 0 ; 

  for (int i = 0; i < 4; i++) {
   buscaBloco(0, 2);
	aux ++;

	if ( aux == IRobo.N_BLOCOS){
	break;
 		}
	}

	if (aux != IRobo.N_BLOCOS){
		this.mensageiro.msgNaoAchou(); //(se não achou os 4 blocos)
	
		}
	}

 /**
 	Retorna instância do mensageiro do robô
 */
 public Mensageiro mensageiro() {
  return (this.mensageiro);
 }


}
