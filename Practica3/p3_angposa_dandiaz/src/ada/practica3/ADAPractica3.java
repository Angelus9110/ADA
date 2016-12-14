/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada.practica3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ADAPractica3 {

    public final String fileName = "entrada2.txt";
    public static int N = 900000;
    public static ArrayList<Integer>[] g = new ArrayList[N];
    public int[] visited = new int[N];
    private static ArrayList<String> nodeMatrix = new ArrayList<String>();

    public static List<List<Integer>> scc(List<Integer>[] graph) {
        int n = graph.length;
        boolean[] used = new boolean[n];
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(graph, used, order, i);
            }
        }

        List<Integer>[] reverseGraph = new List[n];
        for (int i = 0; i < n; i++) {
            reverseGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                reverseGraph[j].add(i);
            }
        }

        List<List<Integer>> components = new ArrayList<>();
        Arrays.fill(used, false);
        Collections.reverse(order);

        for (int u : order) {
            if (!used[u]) {
                List<Integer> component = new ArrayList<>();
                dfs(reverseGraph, used, component, u);
                components.add(component);
            }
        }

        return components;
    }

    static void dfs(List<Integer>[] graph, boolean[] used, List<Integer> res, int u) {
        used[u] = true;
        for (int v : graph[u]) {
            if (!used[v]) {
                dfs(graph, used, res, v);
            }
        }
        res.add(u);
    }

    public void makeGraph() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String str;
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<Integer>(1);
            visited[i] = 0;
        }
        while ((str = br.readLine()) != null) {    
            String[] usuarios = str.split(" ");
            if (!nodeMatrix.contains(usuarios[0])) {
                nodeMatrix.add(usuarios[0]);
            }
        }
        N = nodeMatrix.size();
        while ((str = br2.readLine()) != null) {  
            String[] usuarios = str.split(" ");
            Integer i = nodeMatrix.indexOf(usuarios[0]);
            Integer j = nodeMatrix.indexOf(usuarios[2]);
            g[i].add(j);
        }
        br.close();
    }
    // Usage example

    public static void main(String[] args) throws IOException {
        ADAPractica3 k = new ADAPractica3();
        k.makeGraph();

        List<List<Integer>> components = scc(g);
        for (List<Integer> component : components) {
            if (component.size() >= 3) {
                for (Integer integer : component) {
                    System.out.print(nodeMatrix.get(integer) + " ");
                }
                System.out.println("");
            }
        }
    }
}
