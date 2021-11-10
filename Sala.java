
/*********************************************************************/
/** ACH 2002 - Introducao à Análise de Algoritmos                   **/
/** EACH-USP - Segundo Semestre de 2018                             **/
/**                                                                 **/
/** <Maraiza Adami Pereira > 	                                    **/
/**                                                                 **/
/*********************************************************************/

package robo;

/**
	Classe que define a sala por onde o robô passeia. A sala é uma matriz quadrada de largura ISala.TAMANHO_SALA.
	
	Essa classe deve ser implementada pelo aluno
*/
public class Sala implements ISala {
 
    
	// cria matriz bidimensional
    public int[][] Tabuleiro = new int[TAMANHO_SALA][TAMANHO_SALA]; 
            

	//construtor da classe 
	 public Sala() {
            
             for (int x =X_INICIO_ARM; x <= TAMANHO_SALA-1; x ++){
	                   for ( int y = Y_FIM_ARM+1 ; y <= TAMANHO_SALA-1; y++){
	                Tabuleiro[x][y] = POSICAO_VAZIA;
                           }
                           }
               
	   
	      	  }
	    
	
//Remove um determinado marcador de toda a área de busca da sala.
	public void removeMarcador (int marcador ){ 
		if (marcador == MARCA_PRESENTE || marcador == BLOCO_PRESENTE || marcador == OBSTACULO_PRESENTE) { 
             		marcador = POSICAO_VAZIA ;
       			 }
			}

//Remove o marcador de uma determinada posição da área de busca (exceto OBSTACULO_PRESENTE, que deixa intacto).
	public void removeMarcador (int x, int y) { 
		if ((Tabuleiro[x][y] == MARCA_PRESENTE) || (Tabuleiro[x][y] == BLOCO_PRESENTE)) {
          		  Tabuleiro[x][y] = POSICAO_VAZIA;
        		}
		}

    	public boolean areaArmazenagem (int x, int y) {
           
            if (Tabuleiro[x][y] >= Tabuleiro[0][0]) {
                if ( Tabuleiro[x][y] <= Tabuleiro[1][1]){
                }
            return true;
            
                } else{
                    return false;
                }
       }
  
           
//Verifica se uma determinada posição da sala está marcada
        
	public int marcadorEm (int x, int y) {
            
        
		if (Tabuleiro[x][y] == 1) {
          		 return 1;
      		 } else {
            		return 0;
       		 }
 	}

//Marca uma determinada posição de armazenagem com um bloco.false se as coordenadas não forem válidas para armazenagem, ou então se a coordenada já estiver marcada, true caso consiga inserir o marcador BLOCO_PRESENTE na posição.
    	public boolean marcaPosicaoArmazenagem (int x, int y) { 
 		
             if  (areaArmazenagem(x,y) == true ){ 
              
            if (Tabuleiro[0][0] == POSICAO_VAZIA){
		 Tabuleiro[0][0]= BLOCO_PRESENTE;
                 
                 return true;
               }
           
              if (Tabuleiro[0][1] == POSICAO_VAZIA){
		Tabuleiro[0][1]= BLOCO_PRESENTE;  
                 return true;
             }
             if (Tabuleiro[1][0] == POSICAO_VAZIA){
		Tabuleiro[1][0]= BLOCO_PRESENTE;
                 return true;
             }
             
             if (Tabuleiro[1][1] == POSICAO_VAZIA){
		// armazenagem [1][1] = Tabuleiro[x][y];
		 Tabuleiro[1][1]= BLOCO_PRESENTE; 
             return true; 
            }
             }
        return false;   
           }
// Marca uma determinada posicao de busca com o marcador fornecido.
 	public boolean marcaPosicaoBusca (int x, int y, int marcador) {
		Tabuleiro[x][y] = marcador;
            		 return true; 
		}
 //Verifica se a posição de busca é válida.    	
	public boolean posicaoBuscaValida(int x, int y) {
	       if ((x <= X_FIM_ARM && y <= Y_FIM_ARM) || (x >= TAMANHO_SALA || y >= TAMANHO_SALA)){
        	   return false;
                  }
        else{
	   return true;
                   
		}
	}
}

