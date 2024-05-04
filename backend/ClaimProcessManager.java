/**
 * @author <LE MINH THAI HOA - S3979194>
 */

package backend;

import java.util.List;


public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(Claim claim);
    Claim getOne(String id);
    List<Claim> getAll();

}