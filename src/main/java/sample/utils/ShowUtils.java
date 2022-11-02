package sample.utils;

public class ShowUtils {
    public static String showContent(String gadgetName){
        if (gadgetName.equals("cc4")){
            return "->PriorityQueue.readObject()\n" +
                    "      ->PriorityQueue.heapify()\n" +
                    "          ->PriorityQueue.siftDown()\n" +
                    "            ->PriorityQueue.siftDownUsingComparator()\n" +
                    "                 ->TransformingComparator.compare()\n" +
                    "                    ->ChainedTransformer.transform()\n" +
                    "                        ->ConstantTransformer.transform()\n" +
                    "                            ->InstantiateTransformer.transform()\n" +
                    "                                ->TrAXFilter.TrAXFilter()\n" +
                    "                                   ->TemplatesImpl.newTransformer()\n" +
                    "dependency: commons-collections4: 4.0";
        } else if (gadgetName.equals("cc6")){
            return "->Hashmap.readObject()\n" +
                    "      ->Hashmap.hash()\n" +
                    "          ->TiedMapEntry.hashcode()\n" +
                    "            ->TiedMapEntry.getValue()\n" +
                    "                 ->LazyMap.get()\n" +
                    "                    ->InvokerTransformer.transform()\n" +
                    "                        ->TemplatesImpl.newTransformer()\n" +
                    "dependency: commons-collections: 3.2.1";
        } else if (gadgetName.equals("cc2")){
            return "->PriorityQueue.readObject()\n" +
                    "      ->PriorityQueue.heapify()\n" +
                    "          ->PriorityQueue.siftDown()\n" +
                    "            ->PriorityQueue.siftDownUsingComparator()\n" +
                    "                 ->TransformingComparator.compare()\n" +
                    "                    ->ChainedTransformer.transform()\n" +
                    "                        ->InvokerTransformer.transform()\n" +
                    "                            ->TemplatesImpl.newTransformer()\n" +
                    "dependency: commons-collections4: 4.0";
        } else if (gadgetName.equals("cc5")){
            return "->BadAttributeValueExpException.readObject()\n" +
                    "      ->BadAttributeValueExpException.toString()\n" +
                    "          ->TiedMapEntry.toString()\n" +
                    "            ->TiedMapEntry.getValue()\n" +
                    "                 ->LazyMap.get()\n" +
                    "                    ->InvokerTransformer.transform()\n" +
                    "                        ->TemplatesImpl.newTransformer()\n" +
                    "dependency: commons-collections: 3.2.1";
        } else if (gadgetName.equals("cc7")){
            return "->Hashtable.readObject()\n" +
                    "      ->Hashtable.reconstitutionPut()\n" +
                    "            ->AbstractMapDecorator.equals()\n" +
                    "                ->AbstractMap.equals()\n" +
                    "                  ->LazyMap.get()\n" +
                    "                    ->ChainedTransformer.transform()\n" +
                    "                      ->ConstantTransformer.transform()\n" +
                    "                        ->InstantiateTransformer.transform()\n" +
                    "                           ->TrAXFilter.TrAXFilter()\n" +
                    "                              ->TemplatesImpl.newTransformer()\n" +
                    "dependency: commons-collections: 3.2.1";
        } else if (gadgetName.equals("rome1.0")){
            return "->Hashmap.readObject()\n" +
                    "   ->Hashmap.hash()\n" +
                    "       ->ObjectBean.hashcode()\n" +
                    "           ->EqualsBean.beanHashCode()\n" +
                    "               ->ObjectBean.toString()\n" +
                    "                   ->ToStringBean.toString()\n" +
                    "                      ->TemplatesImpl.getOutputProperties()\n" +
                    "dependency: rome: 1.0";
        } else if (gadgetName.equals("rome1.7")){
            return "->Hashmap.readObject()\n" +
                    "   ->Hashmap.hash()\n" +
                    "       ->EqualsBean.hashcode()\n" +
                    "           ->EqualsBean.beanHashCode()\n" +
                    "               ->ToStringBean.toString()\n" +
                    "                   ->SignedObject.getObject()->TemplatesImpl.readObject()\n" +
                    "                       ->Hashmap.readObject()\n" +
                    "                           ->Hashmap.hash()\n" +
                    "                              ->ObjectBean.hashcode()\n" +
                    "                                  ->EqualsBean.beanHashCode()\n" +
                    "                                      ->ObjectBean.toString()\n" +
                    "                                          ->ToStringBean.toString()\n" +
                    "                                              ->TemplatesImpl.getOutputProperties()\n" +
                    "                                                  ->TemplatesImpl.getOutputProperties()\n" +
                    "dependency: rome: 1.7";
        } else if (gadgetName.equals("commons-beanutils")){
            return "->PriorityQueue.readObject()\n" +
                    "      ->PriorityQueue.heapify()\n" +
                    "          ->PriorityQueue.siftDown()\n" +
                    "            ->PriorityQueue.siftDownUsingComparator()\n" +
                    "                 ->BeanComparator.compare()\n" +
                    "                    ->TemplatesImpl.getOutputProperties()\n" +
                    "dependency: commons-beanutils: 1.9.4";
        }
        return "";
    }

    public static String showMemShellUse(String memShellType){
        if (memShellType.equals("Interceptor")){
            return "usage: http://hostname/?cmd=id\n" +
                    "useful for spring(maybe)";
        } else if (memShellType.equals("Tomcat-Filter")){
            return "usage: http://hostname/asd?cmd=id\n" +
                    "NOTE:please send payload twice to the server.\n" +
                    "Some tomcat version maybe not useful";
        } else if (memShellType.equals("Spring-Controller")){
            return "usage: http://hostname/asd?cmd=id\n" +
                    "just for spring";
        } else if (memShellType.equals("Listener")){
            return "usage: http://hostname/?cmd=id\n" +
                    "Listener mybe fun ^^";
        } else if (memShellType.equals("Tomcat-Servlet")){
            return "usage: http://hostname/asd?cmd=id\n" +
                    "For Tomcat XD";
        } else if (memShellType.equals("Upgrade")){
            return "usage: add these to HTTP header \n" +
                    "-----------------------------\n" +
                    "Connection: upgrade\n" +
                    "Upgrade: asd\n" +
                    "cmd: id\n" +
                    "-----------------------------\n" +
                    "upgrade maybe useful orz";
        } else {
            return "usage: http://hostname/?cmd=id\n" +
                    "amazing @_@";
        }
    }
}
