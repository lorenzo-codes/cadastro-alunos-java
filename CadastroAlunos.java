import java.util.ArrayList;
import java.util.Scanner;

// Classe Aluno
 class Aluno {
    String nome;
    ArrayList<Double> notas = new ArrayList<>();
    double media;

    public Aluno(String nome) { // Construtor da classe Alunos
        this.nome = nome;

    }

    public void adicionarNota(double nota) { // Adiciona uma nota ao aluno
        notas.add(nota);
    }

    public void calcularMedia() { //Calcula a média das notas dos alunos
        double soma = 0;
    for (double nota : notas) {
            soma += nota;
        }
        media = soma / notas.size();

    }

    public void mostrarInformacoes() { // Mostra as informações do aluno
        System.out.println("Aluno: " + nome);
        System.out.printf("Media final: %.2f\n", media);
    
        if (media >= 7) {
            System.out.println("Situação: Aprovado");
        } else if (media >= 5) {
            System.out.println("Situação: Recuperação");
        } else {
            System.out.println("Situação: Reprovado");
        }


        System.out.println("------------------------");
        }

        public void atualizarNotas(Scanner sc) { // Atualiza as notas do aluno
            notas.clear(); // Apagar notas anteriores
            System.out.print("Quantas notas deseja informar novamente para " + nome + "? ");
            int quantidadeNotas = sc.nextInt();

            for (int i = 1; i <= quantidadeNotas; i++) {
                System.out.print("Digite a nova nota " + i + ": ");
                double nota = sc.nextDouble();
                adicionarNota(nota);

            }

            calcularMedia(); // Recalcula a média após atualizar as notas
            System.out.println("Notas atualizadas com sucesso!");

            }
}

// Classe Principal
public class CadastroAlunos {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Aluno> listaAlunos = new ArrayList<>();

        System.out.print("Quantos alunos deseja cadastrar? ");
        int quantidadeAlunos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < quantidadeAlunos; i++) {
            System.out.println("\nCadastro do aluno " + ( i + 1));
            System.out.print("Nome do aluno: ");
            String nome = sc.nextLine();

            Aluno aluno = new Aluno(nome);

            System.out.print("Quantas notas deseja informar para " + nome + "? ");
            int quantidadeNotas = sc.nextInt();

            for (int j = 1; j <= quantidadeNotas; j++) {
                System.out.print("Digite a nota " + j + ": ");
                double nota = sc.nextDouble();
                aluno.adicionarNota(nota);

            }

            aluno.calcularMedia(); // Calcula a média após adicionar as notas
            listaAlunos.add(aluno); // Adiciona o aluno à lista
            sc.nextLine(); // Limpar o buffer

        }


        //Menu para buscar e atualizar notas
        int opcao;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Exibir todos os alunos");
            System.out.println("2. Buscar aluno por nome");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    for (Aluno a : listaAlunos) {
                        a.mostrarInformacoes();

                    }
                    break;


                    case 2:
                        System.out.print("Digite o nome do aluno que deseja buscar: ");
                        String nomeBusca = sc.nextLine();
                        boolean encontrado = false;

                        for (Aluno a : listaAlunos) {
                            if (a.nome.equalsIgnoreCase(nomeBusca)) {
                                System.out.println("\nAluno encontrado:");
                                a.mostrarInformacoes();

                                System.out.print("Deseja atualizar as notas deste aluno? (s/n): ");
                                String resposta = sc.nextLine();

                                if (resposta.equalsIgnoreCase("s")) {
                                    a.atualizarNotas(sc);
                                    System.out.println("Dados atualizados com sucesso!");
                                    a.mostrarInformacoes();
                                    
                                }


                                encontrado = true;
                                break;
                                
                            }
                        }

                        if (!encontrado) {
                            System.out.println("Aluno não encontrado.");
                        }
                        break;

                      case 3:
                      System.out.println("Saindo do sistema...");
                      break;
                      
                      default:
                      System.out.println("Opção inválida.");

            }

        } while (opcao != 3);

        sc.close(); // Fecha o scanner

    }
}