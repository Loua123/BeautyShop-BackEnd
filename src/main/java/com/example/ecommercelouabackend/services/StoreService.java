package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.dto.addStoreRequest;
import com.example.ecommercelouabackend.entities.Store;
import com.example.ecommercelouabackend.entities.User;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.StoreRepo;
import com.example.ecommercelouabackend.repositories.UserRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements ICurd<Store> {
    private final StoreRepo storeRepo;
    private final UserRepo userRepo;

    public StoreService(StoreRepo storeRepo, UserRepo userRepo) {
        this.storeRepo = storeRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Store add(Store store) throws NotFoundEntityException {
        if (store != null)
        return this.storeRepo.save(store);
        throw new NotFoundEntityException("no Store found , Store is null");
    }
    @Override
    public void delete(long id) {
        this.storeRepo.findById(id)
                .ifPresent(this.storeRepo::delete);
    }
    @Override
    public void update(Store store) {
        if (store != null) {
            storeRepo.save(store);
        }
    }
    @Override
    public Store get(long id) throws NotFoundEntityException {
        return this.storeRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found Store with id " + id));
    }
    @Override
    public List<Store> getAll() {
        return this.storeRepo.findAll();
    }
    public boolean addStore(addStoreRequest store) {
        Optional<User> user = userRepo.findById(store.getIduser());
        if (user.isPresent()) {
            User userObject = user.get(); // Get the actual User object from Optional<User>
            Store storetoAdd = new Store();
            storetoAdd.setStatus(false);
            storetoAdd.setName(store.getNameStore());
            storetoAdd.setVille(store.getVilleStore());
            storetoAdd.setAdresse(store.getAdresseStore());
            storetoAdd.setCodepostal(store.getCodepostalStore());
            storetoAdd.setOwnerDOB(store.getOwnerDOB());
            storetoAdd.setMatriculefiscale(store.isMatriculefiscaleStore());
            storetoAdd.setValueOfmatriculefiscale(store.getValueOfmatriculefiscaleStore());
            storetoAdd.setUser(userObject);
            storetoAdd.setImg_de_piece(store.getImg_de_pieceStore());
            storeRepo.save(storetoAdd);
            userObject.getStores().add(storetoAdd);
            userRepo.save(userObject);
            return true; // Store added successfully
        }
        return false; // User not found, store not added
    }

}