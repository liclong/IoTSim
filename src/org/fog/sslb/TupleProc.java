package org.fog.sslb;

/**
 * Created by ChrisLi on 10/20/17.
 */

import org.cloudbus.cloudsim.Cloudlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

public class TupleProc {


    public TupleProc(){}

    public static List<Cloudlet> getInfo(int userId){

        LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
        UtilizationModel utilizationModel = new UtilizationModelFull();

        try {

            StringBuffer sb= new StringBuffer("");

            FileReader reader = new FileReader("/Users/ChrisLi/Cloud/GitProject/SimSSLB/src/org/fog/resource/cloudletinfo");
            BufferedReader br = new BufferedReader(reader);



            String str = null;
            int idShift = 0;
            while((str = br.readLine()) != null) {

                //sb.append(str+"/n");
                //System.out.println(str);

                String[] tmp = str.split(":");

                long length = Long.valueOf(tmp[2]).longValue();
                long fileSize = Long.valueOf(tmp[3]).longValue();
                long outputSize = Long.valueOf(tmp[4]).longValue();
                int pesNumber = 1;

                Cloudlet cloudlet = new Cloudlet(idShift++, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);

                //cloudlet.setUserId(Integer.valueOf(tmp[0]).intValue());
                cloudlet.setUserId(userId);
                //cloudlet.setVmId(Integer.valueOf(tmp[1]).intValue());
                cloudlet.setVmId(0);

                //timestamp = Double.valueOf(tmp[5]);
                //System.out.println(timestamp);
                list.add(cloudlet);

            }

            br.close();
            reader.close();

            // write string to file

            //FileWriter writer = new FileWriter("./test2.txt");
            //BufferedWriter bw = new BufferedWriter(writer);
            //bw.write(sb.toString());

            //bw.close();
            //writer.close();

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    }
