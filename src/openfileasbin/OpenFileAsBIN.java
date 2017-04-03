/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openfileasbin;

import Funciones.IOBinFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 
 */
public class OpenFileAsBIN 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
      
        float[] v = new float[2];
        String resultado = null;
        String nombre_archivo=null;
        int posicion=-1;
        int contador=0;
        
        File f = new File(args[0]);
        String list[]=f.list();
        
        System.out.println("args[0] "+args[1]);
        
        if(f.isFile())//es un archivo
        {
             v = IOBinFile.ReadBinFloatFileIEEE754(f.getAbsolutePath());
             //escritura en un txt
        }
        else 
            if(f.isDirectory())//es una carpeta
            {
                for (int i = 0; i <list.length; i++)//recorremos todos los archivos que estan en la carpeta 
                {
                    posicion=list[i].indexOf(".");//buscamos en que posicion se encuentre .
                    contador=contador+1;//incrementamos el contador para saber cuantos archivos hizo el programa

                    nombre_archivo=list[i].substring(0, posicion)+".txt";//creamos el nuevo nombre del arhivo txt

                    System.out.println("Archivo: "+nombre_archivo);

                    v = IOBinFile.ReadBinFloatFileIEEE754(args[1]+File.separator+list[i]);//leemos el archivo
                    
                    for (int j = 0; j < v.length; j++) //recorremos el contenido del archivo
                    {
                        if(resultado == null)
                        {
                            resultado=""+v[j];
                        }
                        else
                        {
                            resultado=resultado+","+v[j];//separamos por comas
                        }

                    }
                    
                    File archivo = new File("C:\\Users\\DELL\\Desktop\\youtube8M\\reduced\\train\\"+nombre_archivo);
                    BufferedWriter bw = null;
                    try 
                    {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write(resultado);
                        bw.close();
                    } catch (IOException ex) {
                        System.out.println("No se pudo crear el archivo");

                    }

                    System.out.println("Resultado: "+resultado);
                    resultado=null;
                }
                
                System.out.println("Contador: "+contador);
        }
    }
}
