<a href="index.php?page=home" <?php if($_GET['page'] == 'home') { ?>class="tab-active"<?php } ?>><i class="fa fa-home"></i> Home</a>                
<a href="showsplaysheets.php?page=shows" <?php if($_GET['page'] == 'shows') { ?>class="tab-active"<?php } ?>><i class="fa fa-list"></i> Shows & Playshe..</a>
<a href="songsartists.php?page=songs" <?php if($_GET['page'] == 'songs') { ?>class="tab-active"<?php } ?>><i class="fa fa-caret-square-o-right"></i> Songs, artists, ...</a>
<a href="guestshosts.php?page=guests" <?php if($_GET['page'] == 'guests') { ?>class="tab-active"<?php } ?>><i class="fa fa-users"></i> Guest & Hosts</a>
<a href="soundcloud.php?page=soundcloud" <?php if($_GET['page'] == 'soundcloud') { ?>class="tab-active"<?php } ?>><i class="fa fa-cloud"></i> Soundcloud</a>
<a href="modify.php?page=modify" <?php if($_GET['page'] == 'modify') { ?>class="tab-active"<?php } ?>><i class="fa fa-cogs"></i> Modify Data</a>