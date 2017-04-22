package com.example.nguye.rssexpress;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by nguye on 19/04/2017.
 */

public class ReadXML extends AsyncTask<Void,Void,Void> {
    Context context;
    ProgressDialog progressDialog;

    String address="http://vnexpress.net/rss/tin-moi-nhat.rss";
   // String address="http://www.sciencemag.org/rss/news_current.xml";
    URL url;
    ArrayList<Feeditem> feeditem;
    RecyclerView recycle;
    public ReadXML(Context context,RecyclerView re) {
        this.context = context;
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        recycle=re;


    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Myadapter adapter=new Myadapter(context,feeditem);
        recycle.setLayoutManager(new LinearLayoutManager(context));
        recycle.addItemDecoration(new VerticalSpace(50));
        recycle.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        Processxml(Getdata());
        return null;
    }

    private void Processxml(Document data) {
        if(data!=null){
           feeditem=new ArrayList<>();
            Element root=data.getDocumentElement();
            Node channel=root.getChildNodes().item(1);
            NodeList items=channel.getChildNodes();
            for (int i=0;i<items.getLength();i++){
                Node cureentchild=items.item(i);
                if(cureentchild.getNodeName().equalsIgnoreCase("item")){
                    Feeditem item=new Feeditem();
                    NodeList itemchilds=cureentchild.getChildNodes();
                    for (int j=0;j<itemchilds.getLength();j++){
                        Node cureent=itemchilds.item(j);
                       // Log.d("textcontent", cureent.getTextContent());
                        if(cureent.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(cureent.getTextContent());

                        }else if(cureent.getNodeName().equalsIgnoreCase("description")){
                            Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                            Pattern nd=Pattern.compile("<br['\"][^>]*");
                            String url=cureent.getChildNodes().item(0).getTextContent();
                            Matcher matcher=p.matcher(url);
                            Matcher matchind=nd.matcher(url);
                            if(matcher.find()){
                                item.setGuid(matcher.group(1));
                            }else if(matchind.find())
                            {
                                item.setDescription(matchind.group(1));
                            }



                        }else if(cureent.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(cureent.getTextContent());

                        }else if(cureent.getNodeName().equalsIgnoreCase("link")){

                            item.setLink(cureent.getTextContent());
                        }
//                        else if(cureent.getNodeName().equalsIgnoreCase("description")){
//                            String url=cureent.getChildNodes().item(0).getTextContent();
//                            item.setGuid(url);
//                            Log.d("kq", url);
//                        }

                    }
                    feeditem.add(item);


                }
            }
        }

    }

    public Document Getdata(){
        try {
            url=new URL(address);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream=connection.getInputStream();
            DocumentBuilderFactory builderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=builderFactory.newDocumentBuilder();
            Document xmldoc=builder.parse(inputStream);
            return  xmldoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
