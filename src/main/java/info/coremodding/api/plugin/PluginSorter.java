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

    private ModPlugin beforeAll = new CAPluginContainer("DummyBeforeAll");
    private ModPlugin afterAll = new CAPluginContainer("DummyAfterAll");
    private ModPlugin before = new CAPluginContainer("DummyBefore");
    private ModPlugin after = new CAPluginContainer("DummyAfter");

    public PluginSorter(List<ModPlugin> modList, Map<String, ModPlugin> nameLookup)
    {
        buildGraph(modList, nameLookup);
    }

    private void buildGraph(List<ModPlugin> modList, Map<String, ModPlugin> nameLookup)
    {
        modGraph = new DirectedGraph<ModPlugin>();
        modGraph.addNode(beforeAll);
        modGraph.addNode(before);
        modGraph.addNode(afterAll);
        modGraph.addNode(after);
        modGraph.addEdge(before, after);
        modGraph.addEdge(beforeAll, before);
        modGraph.addEdge(after, afterAll);

        for (ModPlugin mod : modList)
        {
            modGraph.addNode(mod);
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
                    modGraph.addEdge(mod, afterAll);
                    modGraph.addEdge(after, mod);
                    postDepAdded = true;
                }
                else
                {
                    modGraph.addEdge(before, mod);
                    if (nameLookup.containsKey(dep)) {
                        modGraph.addEdge(nameLookup.get(dep), mod);
                    }
                }
            }

            for (String dep : mod.getPostDepends())
            {
                postDepAdded = true;

                if (dep.equals("*"))
                {
                    // We are "before" everything
                    modGraph.addEdge(beforeAll, mod);
                    modGraph.addEdge(mod, before);
                    preDepAdded = true;
                }
                else
                {
                    modGraph.addEdge(mod, after);
                    if (nameLookup.containsKey(dep)) {
                        modGraph.addEdge(mod, nameLookup.get(dep));
                    }
                }
            }

            if (!preDepAdded)
            {
                modGraph.addEdge(before, mod);
            }

            if (!postDepAdded)
            {
                modGraph.addEdge(mod, after);
            }
        }
    }

    public List<ModPlugin> sort()
    {
        List<ModPlugin> sortedList = TopologicalSortP.topologicalSort(modGraph);
        sortedList.removeAll(Arrays.asList(new ModPlugin[] {beforeAll, before, after, afterAll}));
        return sortedList;
    }
}