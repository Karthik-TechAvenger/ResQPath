
package General.EALEServer;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/myresource")
public class MyResource {

    private AssetDAO assetDAO = new AssetDAOImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asset> getAllAssets() {
        try {
            return assetDAO.getAllAssets();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
