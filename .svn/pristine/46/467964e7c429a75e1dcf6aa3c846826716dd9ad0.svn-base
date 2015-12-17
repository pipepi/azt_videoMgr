
    <?php
        $hostId = $_POST["hid"];
        $hostValue = $_POST["hValue"];
        //........................一系列操作
        //最后
        // echo json_encode(array('code'=>0,'result'=>$arr));exit;
        
        $file ="../xml/host.xml";
        //创建DOMDocument的对象
        $dom=new DOMDocument('1.0');
        //载入host.xml文件
        $dom->load($file);
        //获得record节点的集合
        $hosts = $dom->getElementsByTagName('host_list');
        //遍历record节点的集合
        foreach($hosts as $host){
                //9cooo
                if($hostId == "9cooomall")
                {
                        $host->getElementsByTagName('mall')->item(0)->nodeValue = $hostValue;
                }

                if($hostId == "9cooovideo")
                {
                        $host->getElementsByTagName('video')->item(0)->nodeValue = $hostValue;
                }
                if($hostId == "9cooovideoImage")
                {
                        $host->getElementsByTagName('videoImage')->item(0)->nodeValue = $hostValue;
                }
                // cince
                if($hostId == "cincemall")
                {
                        $host->getElementsByTagName('mall')->item(1)->nodeValue = $hostValue;
                }

                if($hostId == "cincevideo")
                {
                        $host->getElementsByTagName('video')->item(1)->nodeValue = $hostValue;
                }

                if($hostId == "cincevideoImage")
                {
                        $host->getElementsByTagName('videoImage')->item(1)->nodeValue = $hostValue;
                }
                // dev
                if($hostId == "devmall")
                {
                        $host->getElementsByTagName('mall')->item(2)->nodeValue = $hostValue;
                }

                if($hostId == "devvideo")
                {
                        $host->getElementsByTagName('video')->item(2)->nodeValue = $hostValue;
                }

                if($hostId == "devvideoImage")
                {
                        $host->getElementsByTagName('videoImage')->item(2)->nodeValue = $hostValue;
                }
                // test
                if($hostId == "testmall")
                {
                        $host->getElementsByTagName('mall')->item(3)->nodeValue = $hostValue;
                }

                if($hostId == "testvideo")
                {
                        $host->getElementsByTagName('video')->item(3)->nodeValue = $hostValue;
                }

                if($hostId == "testvideoImage")
                {
                        $host->getElementsByTagName('videoImage')->item(3)->nodeValue = $hostValue;
                }
          
        }
        $dom->save('../xml/host.xml');
        exit;
    ?>
