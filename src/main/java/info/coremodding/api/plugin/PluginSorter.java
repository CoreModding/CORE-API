package info.coremodding.api.plugin;

import info.coremodding.api.plugin.TopologicalSortP.DirectedGraph;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author cpw
 * 
 */
public class PluginSorter
{
    private DirectedGraph<ModPlugin> modGraph;
    
    private final ModPlugin          beforeAll = new CAPluginContainer("DummyBeforeAll");
    private final ModPlugin          afterAll  = new CAPluginContainer("DummyAfterAll");
    private final ModPlugin          before    = new CAPluginContainer("DummyBefore");
    private final ModPlugin          after     = new CAPluginContainer("DummyAfter");
    
    public PluginSorter(List<ModPlugin> modList, Map<String, ModPlugin> nameLookup)
    {
        buildGraph(modList, nameLookup);
    }
    
    private void buildGraph(List<ModPlugin> modList, Map<String, ModPlugin> nameLookup)
    {
        this.modGraph = new DirectedGraph<>();
        this.modGraph.addNode(this.beforeAll);
        this.modGraph.addNode(this.before);
        this.modGraph.addNode(this.afterAll);
        this.modGraph.addNode(this.after);
        this.modGraph.addEdge(this.before, this.after);
        this.modGraph.addEdge(this.beforeAll, this.before);
        this.modGraph.addEdge(this.after, this.afterAll);
        
        for (ModPlugin mod : modList)
        {
            this.modGraph.addNode(mod);
        }
        
        for (ModPlugin mod : modList)
        {
            boolean preDepAdded = false;
            boolean postDepAdded = false;
            
            for (String dep : mod.getPreDepends())
            {
                preDepAdded = true;
                
                if (dep.equals("*"))
                {
                    // We are "after" everything
                    this.modGraph.addEdge(mod, this.afterAll);
                    this.modGraph.addEdge(this.after, mod);
                    postDepAdded = true;
                }
                else
                {
                    this.modGraph.addEdge(this.before, mod);
                    if (nameLookup.containsKey(dep))
                    {
                        this.modGraph.addEdge(nameLookup.get(dep), mod);
                    }
                }
            }
            
            for (String dep : mod.getPostDepends())
            {
                postDepAdded = true;
                
                if (dep.equals("*"))
                {
                    // We are "before" everything
                    this.modGraph.addEdge(this.beforeAll, mod);
                    this.modGraph.addEdge(mod, this.before);
                    preDepAdded = true;
                }
                else
                {
                    this.modGraph.addEdge(mod, this.after);
                    if (nameLookup.containsKey(dep))
                    {
                        this.modGraph.addEdge(mod, nameLookup.get(dep));
                    }
                }
            }
            
            if (!preDepAdded)
            {
                this.modGraph.addEdge(this.before, mod);
            }
            
            if (!postDepAdded)
            {
                this.modGraph.addEdge(mod, this.after);
            }
        }
    }
    
    public List<ModPlugin> sort()
    {
        List<ModPlugin> sortedList = TopologicalSortP.topologicalSort(this.modGraph);
        sortedList.removeAll(Arrays.asList(new ModPlugin[] { this.beforeAll, this.before, this.after, this.afterAll }));
        return sortedList;
    }
}