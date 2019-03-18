import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class matriks {
    int IdxMax = 10;
    //Atribut
    int Brs;
    int Kol;
    float[][] Mat = new float[IdxMax][IdxMax];
    float[][] Tampung = new float[IdxMax][IdxMax];

    //Scanner
    Scanner keyboard = new Scanner(System.in);

    matriks(){
    /* Konstruktor matriks, membuat matriks berukuran IdxMax x IdxMax
    dan mengatur semua nilai dari entri matriks bernilai 0*/
        int i,j;

        for (i = 1; i < IdxMax; i++){
            for (j = 1; j < IdxMax; j++){
                this.Mat[i][j] = 0;
            }
        }

        Brs = 0;
        Kol = 0;
    }

    void bacaUkuranMatriks(){
    //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
        System.out.print("Masukan banyaknya baris : ");
        Brs = keyboard.nextInt();
        System.out.print("Masukan banyaknya kolom : ");
        Kol = keyboard.nextInt();
    }

    void bacaUkuranMatriksInterpolasi(){
    //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
        System.out.print("Masukan banyaknya baris : ");
        Brs = keyboard.nextInt();
        Kol = 2;
    }

    
    float pangkat(float x,int i)
    {
        int j;
        float xtemp;

        xtemp = x;
        for(j=1;j<=i-1;j++)
        {
            x = x * xtemp;
        }
        return (x);
    }
    void bacaMatriks(){
    //Membaca ukuran lalu membaca inputan user untuk membentuk sebuah matriks
        int i,j;

        bacaUkuranMatriks();

        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = keyboard.nextFloat();
            }
        }
    }

    void bacaMatriksInterpolasi(){
        int i,j;

        bacaUkuranMatriksInterpolasi();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = keyboard.nextFloat();
            }
        }

    }

    void matriksInterpolasi()
    {
        int i,j;
        float x,y;

        bacaMatriksInterpolasi();
        
        for(i=1;i<=Brs;i++)
        {
            x = this.Mat[i][1];
            y = this.Mat[i][2];
            this.Mat[i][1] = 1;  
            for(j=2;j<=Brs;j++)
            {
                this.Mat[i][j] = pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }
	
	void matriksInterpolasiExt()
    {
        int i,j;
        float x,y;

        bacaFileExtInterpolasi();
        
        for(i=1;i<=Brs;i++)
        {
            x = this.Mat[i][1];
            y = this.Mat[i][2];
            this.Mat[i][1] = 1;  
            for(j=2;j<=Brs;j++)
            {
                this.Mat[i][j] = pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }

    void solusiInterpolasi(){
        int i,j,k,l;
        float x,sum;
        String s,s2,stemp;
		String writeFileName="OutputInterpolasi.txt";
		String enter="\r\n";
        
        System.out.print("Masukkan nilai x = ");
        x = keyboard.nextFloat();
        s2="";
		try
        {
			FileWriter fileWriter = new FileWriter(writeFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (i = 1; i <= Brs; i++){
				for (j = 1; j < Kol; j++){
					bufferedWriter.append(String.valueOf(this.Mat[i][j]) + " ");
				}
				bufferedWriter.append(String.valueOf(this.Mat[i][Kol]));
				bufferedWriter.append(enter);
			}
    
			bufferedWriter.append(enter);
			
			k=0;
			if (isBarisNol(Brs) & this.Mat[Brs][Kol] != 0)
			{
				s = "Tidak ada polinom yang dapat memenuhi";
				bufferedWriter.append(s);
				System.out.println("Tidak ada polinom yang dapat memenuhi");
			}
			else
			{
				s = "f(x) = ";
				System.out.print("f(x) = ");
				for (i=1;i<=Brs;i++)
				{
					if((this.Mat[i][i] == 1) & (this.Mat[i][Kol] != 0))
					{
						k++;
					}
				}
				l=0;
				sum = 0;
				for(i=1;i<=Brs;i++)
				{
					if( (this.Mat[i][i] == 1) && (this.Mat[i][Kol] != 0))
					{
						l++;
						if (i==1)
						{
						   if(k==1)
						   {
								stemp = String.valueOf(this.Mat[i][Kol]);
								s = s + stemp;
								System.out.println(this.Mat[i][Kol]); 
						   } 
						   else if(k > 1)
						   {
								stemp = String.valueOf(this.Mat[i][Kol]);
								s = s + stemp + " + ";
								System.out.print(this.Mat[i][Kol] + " + "); 
						   } // k < 1 & k not 0
						   sum = sum + this.Mat[i][Kol]; 
						} 
						else if(i==2)
						{
							if (this.Mat[i][Kol] != 1)
							{
								stemp = String.valueOf(this.Mat[i][Kol]);
								if (this.Mat[i][Kol] < 0)
								{
									s = s + "(" + stemp + ")";
									System.out.print("("+this.Mat[i][Kol] +")");
								} else {
									s = s + stemp;
									System.out.print(this.Mat[i][Kol]);
								}
							}
							if ( (k==1) || (l==k))
							{
								s = s + "x";
								System.out.println("x");
							} else
							{
								s = s + "x + ";
								System.out.print("x + ");
							}
							sum += this.Mat[i][Kol] * x;
						} else 
						{
							if (this.Mat[i][Kol] != 1)
							{
								stemp = String.valueOf(this.Mat[i][Kol]);
								if (this.Mat[i][Kol] < 0)
								{
									s = s + "(" + stemp + ")";
									System.out.print("("+this.Mat[i][Kol] +")");
								} else {
								s = s + stemp;
								System.out.print(this.Mat[i][Kol]);
								}
							}
							if ((k==1) || (l==k))
							{
								stemp = String.valueOf(i-1);
								s = s + "x^" + stemp;
								System.out.println( "x^" + (i-1));   
							} else 
							{
								stemp = String.valueOf(i-1);
								s = s + stemp;
								System.out.print("x^" + (i-1) + " + ");
							}
							sum += this.Mat[i][Kol] * pangkat(x,i-1);
						}
					}
				}
				s2 = "f(";
				stemp = String.valueOf(x);
				s2 = s2 + stemp + ") = ";
				stemp = String.valueOf(sum);
				s2 = s2 + stemp;
				System.out.println("f(" + x + ") = " + sum);
			}
			
			bufferedWriter.append(s);
			bufferedWriter.append(enter);
			bufferedWriter.append(s2);

			bufferedWriter.close();

        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ writeFileName + "'");}
    }
	
    void tulisMatriks(){
    //Menampilkan isi matriks ke layar
        int i,j;

        for (i = 1; i <= Brs; i++){
            for (j = 1; j < Kol; j++){
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println(this.Mat[i][Kol]);
        }
    }

    void tukarBaris(int i, int j){
    // Menukar baris ke i dengan baris ke j suatu matriks

        int k;
        float temp;

        for (k = 1;k <= Kol;k++){
            temp = this.Mat[j][k];
            this.Mat[j][k] = this.Mat[i][k];
            this.Mat[i][k] = temp;
        }
    }

    boolean isKolNol(int j, int i){
    //true bila pada kolom j dimulai dari baris i sampai Brs = 0
        boolean cek;

        cek = true;
        if (j >= Kol){
            cek = false;
        } else {
            while (cek & i <= Brs){
                if (this.Mat[i][j] != 0){
                    cek = false;
                } else {
                    i += 1;
                }
            }
        }

        return cek;
    }

    int indeksTakNol(int j, int i){
    // Mengeluarkan indeks pertama yang tidak bernilai 0 dari matriks pada kolom j
    // dimulai dari baris ke i
        int k = i;
        boolean cek = true;

        while (cek & k <= Brs){
            if (this.Mat[k][j] != 0){
                cek = false;
            } else {
                k += 1;
            }
        }
        return k;
    }

    void buatLeadingPoint(int i, int j){
    //Membuat M[i][j] leading point bernilai 1 di baris i
        int k;
        float factor;

        factor = this.Mat[i][j];
        for (k = 1;k <= Kol;k++){
            this.Mat[i][k] = this.Mat[i][k]/factor;
        }
    }

    void buatKolomNolBawah(int j, int i){
    //Pivot di M[i][j]
    //Membuat kolom j nol dimulai dari baris ke i + 1
        int k = i+1;
        int l;
        float factor;

        while (k <= Brs){
            factor = this.Mat[k][j];
            for (l = 1;l <= Kol;l++){
                this.Mat[k][l] = this.Mat[k][l] - factor*this.Mat[i][l];
            }
            k += 1;
        }
    }

    void Gauss(){
    //Merubah matriks menjadi Row-Echelon-Form
        int i,j,k;
        float temp;
        i = 1;
        j = 1;

        while (i <= Brs & j < Kol){
            while (isKolNol(j,i)){
                j += 1;
            }

            if (j < Kol){
                tukarBaris(i,indeksTakNol(j,i));
                buatLeadingPoint(i,j);
                buatKolomNolBawah(j,i);

                i += 1;
                j += 1;
            }
        }

        for (i = 1;i <= Brs; i++){
            for (j = 1;j <= Kol;j++){
                if (this.Mat[i][j] == -0){
                    this.Mat[i][j] = 0;
                }
            }
        }
    }

	void SolusiGauss(){
		int i,j,k,l,x;
		boolean lanjut = true;
		String writeFileName="OutputGauss.txt";
		String enter="\r\n";
		
		try
        {
			FileWriter fileWriter = new FileWriter(writeFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
					bufferedWriter.append(String.valueOf(this.Mat[x][j]) + " ");
				}
				bufferedWriter.append(String.valueOf(this.Mat[x][Kol]));
				bufferedWriter.append(enter);
			}
			bufferedWriter.append(enter);

			for (i = Brs; i >= 1;i--){
				j = indeksPivot(i);
				for (k = indeksPivot(i+1)-1; k > indeksPivot(i); k--){
					this.Tampung[k][Kol+1] = -1;
				}
				this.Tampung[j][Kol] = this.Mat[i][Kol];
			}

			i = Brs;
			while (i >= 1 & lanjut){
				if (isBarisNol(i) & this.Mat[i][Kol] != 0){
					lanjut = false;
				} else if (isBarisNol(i) & this.Mat[i][Kol] == 0){
					i -= 1;
				} else {
					j = indeksPivot(i);
					for (k = Kol -1;k > j;k--){
						if (this.Tampung[k][Kol+1] == -1){
							this.Tampung[j][k] = this.Mat[i][k];
						} else {
							this.Tampung[j][Kol] = this.Tampung[j][Kol] - (this.Tampung[k][Kol])*(this.Mat[i][k]);
							for (l = k + 1;l < Kol;l++){
								this.Tampung[j][l] = this.Tampung[j][l] - (this.Tampung[k][l])*(this.Mat[i][k]);
							}
						}
					}
					i -= 1;
				}
			}

			if (lanjut){
				for (i = Kol-1; i >= 1;i--){
					if (this.Tampung[i][Kol+1] == -1){
						System.out.println("x" + i + " variabel bebas");
						bufferedWriter.append("x" + Integer.toString(i) + " variabel bebas/r/n");
					} else {
						System.out.print("x" + i + " = " + this.Tampung[i][Kol]);
						bufferedWriter.append("x" + Integer.toString(i) + " = " + String.valueOf(this.Tampung[i][Kol]));
						for (j = i+1;j < Kol;j++){
							if (this.Tampung[i][j] != 0){
								System.out.print(" -(" + this.Tampung[i][j] + ")x" + j);
								bufferedWriter.append(" -(" + String.valueOf(this.Tampung[i][j]) + ")x" + Integer.toString(j));
							}
						}
						System.out.println();
						bufferedWriter.append(enter);
					}
				}
			} else 
			{
				System.out.println("Tidak ada solusi\n");
				bufferedWriter.append("Tidak ada solusi\r\n");
			}
			
			bufferedWriter.close();
		}
		
		catch(IOException ex) {
            System.out.println("Error writing to file '"+ writeFileName + "'");}

}

    void SolusiInterpolasiGauss(){
       int i,j,k,l;
       int m,n;
       float sum,x;
       String s,s2,stemp;


    for (i = Brs; i >= 1;i--){
        j = indeksPivot(i);
        for (k = indeksPivot(i+1)-1; k > indeksPivot(i); k--){
            this.Tampung[k][Kol+1] = -1;
        }
        this.Tampung[j][Kol] = this.Mat[i][Kol];
    }

    for (i = Brs; i >= 1;i--){
        j = indeksPivot(i);
        for (k = Kol -1;k > j;k--){
            if (this.Tampung[k][Kol+1] == -1){
                this.Tampung[j][k] = this.Mat[i][k];
            } else {
                this.Tampung[j][Kol] = this.Tampung[j][Kol] - (this.Tampung[k][Kol])*(this.Mat[i][k]);
                for (l = k + 1;l < Kol;l++){
                    this.Tampung[j][l] = this.Tampung[j][l] - (this.Tampung[k][l])*(this.Mat[i][k]);
                }
            }
        }
    }
    sum = 0;
    m = 0;
    n = 0;
    System.out.print("Masukkan nilai x = ");
    x = keyboard.nextFloat();
    s = "f(x) = ";
    System.out.print("f(x) = ");
    for (i=1;i<=(Kol-1);i++)
    {
    	if(this.Tampung[i][Kol] != 0)
    	{
    		m++;
    	}
    }
    for (i=1;i<=(Kol-1);i++)
    {
    	if (this.Tampung[i][Kol] != 0)
		{
			n++;
			if(i == 1)
			{
				sum += this.Tampung[i][Kol];
				stemp = String.valueOf(this.Tampung[i][Kol]);
				s = s + stemp;
				System.out.print(this.Tampung[i][Kol]);
			} else if (i==2)
			{
				sum += this.Tampung[i][Kol] * x;
				stemp = String.valueOf(this.Tampung[i][Kol]);
				s = s + stemp +"x";
				System.out.print(this.Tampung[i][Kol]+"x");
			} else 
			{
				sum += this.Tampung[i][Kol] * pangkat(x,i-1);
				System.out.print(this.Tampung[i][Kol]+ "x^"+i);
				stemp = String.valueOf(this.Mat[i][Kol]);
				s = s + stemp + "x^";
				stemp = String.valueOf(i);
				s = s + stemp;
			}
			if (m==n)
			{
				System.out.println();
			} else
			{
				System.out.print(" + ");
				s = s + " + ";
			}

		}
    }
    System.out.println("f(" + x + ") = "+sum);
    stemp = String.valueOf(x);
    s2 = "f(";
    s2 += stemp;
    s2 += ") = ";
    stemp = String.valueOf(sum);
    s2 += sum;
    }
   
   boolean isNolSemua(int i){
    //Mengembalikan true jika dalam baris tersebut 0 semua kecuali kolom augmented
        boolean cek = true;
        int k = 1;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        }

        return cek;
    }

    int indeksPivot(int i){
    //Mengembalikan indeks pivot point pada baris i
    //Dengan asumsi bukan baris yang berisi 0 semua (isNolSemua = false)
        int k = 1;
        boolean cek = true;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        } //
        return k;
    }

    void buatKolomNolAtas(int i, int j){
    // Membuat kolom j berisi nol semua diatas indeks i
        int k = i - 1;
        int l;
        float factor;

        while (k >= 1){
            factor = this.Mat[k][j];
            for (l = 1;l <= Kol;l++){
                this.Mat[k][l] = this.Mat[k][l] - factor*this.Mat[i][l];
            }
            k -= 1;
        }

    }

    void GaussJordan(){
        int i = Brs;
        int j;

        Gauss();
        while (i >= 1){
            while (isNolSemua(i)){
                i -= 1;
            }
            j = indeksPivot(i);
            buatKolomNolAtas(i,j);

            i -= 1;
        }
    }

    boolean isBarisNol(int i){
    //Menghasilkan true jika baris i bukan augmented isinya 0 semua
        int k = 1;
        boolean cek = true;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        }

        return cek;
    }

    void SolusiGaussJordan(){
        int k = 1;
        int i = Brs;
        int j;
		int x;
        boolean lanjut = true;
		String writeFileName="OutputGaussJordan.txt";
		String enter="\r\n";
		
		try
        {
			FileWriter fileWriter = new FileWriter(writeFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
					bufferedWriter.append(String.valueOf(this.Mat[x][j]) + " ");
				}
				bufferedWriter.append(String.valueOf(this.Mat[x][Kol]));
				bufferedWriter.append(enter);
			}
			bufferedWriter.append(enter);
			
			while (i >= 1 & lanjut){
				if (isBarisNol(i) & this.Mat[i][Kol] != 0){
					lanjut = false;
					System.out.println("Tidak ada Solusi!");
					bufferedWriter.append("Tidak ada Solusi!\r\n");
				} else if (isBarisNol(i) & this.Mat[i][Kol] == 0){
					i -= 1;
				} else {
					j = indeksPivot(i);

					for (k = indeksPivot(i + 1) - 1;k > indeksPivot(i);k--){
						System.out.println("x" + k + " variabel bebas");
						bufferedWriter.append("x" + Integer.toString(k) + " variabel bebas\r\n");
					}
					System.out.print("x" + j + " = " + this.Mat[i][Kol]);
					bufferedWriter.append("x" + Integer.toString(j) + " = " + String.valueOf(this.Mat[i][Kol]));
					for (k = j + 1;k < Kol;k++){
						if (this.Mat[i][k] != 0){
							System.out.print(" -(" + this.Mat[i][k] + ")x" + k);
							bufferedWriter.append(" -(" + String.valueOf(this.Mat[i][k]) + ")x" + Integer.toString(k));
						}
					}
					System.out.println();
					bufferedWriter.append(enter);
					i -= 1;
				}

			}
			
			bufferedWriter.close();
		}
		catch(IOException ex) {
            System.out.println("Error writing to file '"+ writeFileName + "'");}

    }
	
	void bacaFileExtSPL(){
		BufferedReader br = null;
		FileReader fr = null;
		int x,dgt,i,d,j;
		float temp,dtemp;
		boolean dec,min;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("matriksSPL.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			sCurrentLine = br.readLine();
			
			if ((sCurrentLine) == null){
				System.out.println("File Kosong");
			}
			else{
				i=0;
				j=0;
				Kol = 0;
				min=false;
				
				while ((sCurrentLine) != null){						//asumsikan antar elemen matriks pada file eksternal hanya dipisahkan satu spasi
					if (min){
						this.Mat [i][j]=(this.Mat [i][j])*(-1);
					}
					dec = false;
					min = false;
					j = 1;
					temp = 0;
					dgt = 1;
					i++;
					for (x=0;x<=(sCurrentLine.length())-1;x++){
						if ((sCurrentLine.charAt(x))!= ' '){
							if ((sCurrentLine.charAt(x))== '.'){
								dec=true;
								dgt=1;
							}
							else if ((sCurrentLine.charAt(x))== '-'){
								min=true;
							}
							else
							{
								dtemp=(sCurrentLine.charAt(x))-'0';
								if (dec){
									for (d=1;d<=dgt;d++){
										dtemp=dtemp/10;
									}
									dgt++;
									temp=temp+dtemp;
									this.Mat[i][j] = temp;
								}
								else{
									temp=(temp*10)+dtemp;
									this.Mat[i][j] = temp;
								}
							}
						}
						else{
							dec = false;
							temp = 0;
							if (min){
								this.Mat [i][j]=(this.Mat [i][j])*-1;
							}
							j++;
							min=false;
						}
					}
					sCurrentLine = br.readLine();
					if (j>Kol){
						Kol = j;
					}
				}
				if (min){
					this.Mat [i][j]=(this.Mat [i][j])*-1;
				}
				Brs=i;
			}
			
			for (i=1;i<=2;i++){
				for (j=1;j<=3;j++){
					System.out.println(this.Mat[i][j]);
				}
			}
				
		}		

		catch (IOException e) {

			e.printStackTrace();
			System.out.println("Ada kesalahan pada file eksternal.");

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	void bacaFileExtInterpolasi(){
		BufferedReader br = null;
		FileReader fr = null;
		int x,dgt,i,d,j;
		float temp,dtemp;
		boolean dec,min;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("matriksInterpolasi.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			sCurrentLine = br.readLine();
			
			if ((sCurrentLine) == null){
				System.out.println("File Kosong");
			}
			else{
				i=0;
				j=0;
				Kol = 0;
				min=false;
				
				while ((sCurrentLine) != null){						//asumsikan antar elemen matriks pada file eksternal hanya dipisahkan satu spasi
					if (min){
						this.Mat [i][j]=(this.Mat [i][j])*(-1);
					}
					dec = false;
					min = false;
					j = 1;
					temp = 0;
					dgt = 1;
					i++;
					for (x=0;x<=(sCurrentLine.length())-1;x++){
						if ((sCurrentLine.charAt(x))!= ' '){
							if ((sCurrentLine.charAt(x))== '.'){
								dec=true;
								dgt=1;
							}
							else if ((sCurrentLine.charAt(x))== '-'){
								min=true;
							}
							else
							{
								dtemp=(sCurrentLine.charAt(x))-'0';
								if (dec){
									for (d=1;d<=dgt;d++){
										dtemp=dtemp/10;
									}
									dgt++;
									temp=temp+dtemp;
									this.Mat[i][j] = temp;
								}
								else{
									temp=(temp*10)+dtemp;
									this.Mat[i][j] = temp;
								}
							}
						}
						else{
							dec = false;
							temp = 0;
							if (min){
								this.Mat [i][j]=(this.Mat [i][j])*-1;
							}
							j++;
							min=false;
						}
					}
					sCurrentLine = br.readLine();
				}
				if (min){
					this.Mat [i][j]=(this.Mat [i][j])*-1;
				}
				Kol=2;
				Brs=i;
			}
				
		}		

		catch (IOException e) {

			e.printStackTrace();
			System.out.println("Ada kesalahan pada file eksternal.");

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	

}
