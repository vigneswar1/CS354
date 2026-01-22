#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

#define Readend 0
#define Writeend 1

int main() {
    int fd[2];
    pid_t pid;

    if (pipe(fd) == -1) {
        perror("pipe failed");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid < 0) {
        perror("fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
    	int a;int b;char op;
    	printf("enter a : ");
    	scanf("%d",&a);
    	printf("enter b : ");
    	scanf("%d",&b);
    	printf("enter op : ");
    	scanf(" %c",&op);
        close(fd[Readend]);

        write(fd[Writeend],&a, sizeof(int));
        write(fd[Writeend],&b, sizeof(int));
        write(fd[Writeend],&op, sizeof(char));

        close(fd[Writeend]);
        exit(EXIT_SUCCESS);
    }
    else {
    	int a;int b;char op;
        close(fd[Writeend]); 

        read(fd[Readend],&a,sizeof(int));
        read(fd[Readend],&b,sizeof(int));
        read(fd[Readend],&op,sizeof(char));
        if(op =='+'){
        	printf("%d",a+b);
        }else if(op =='-'){
        	printf("%d",a-b);
        }else if(op =='*'){
        	printf("%d",a*b);
        }else if(op == '/'){
        	printf("%d",a/b);
        }else{
        	printf("error op");
        }

        close(fd[Readend]);
        wait(NULL);
    }

    return 0;
}
