package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class DRManager extends Contract {
    public static final String BINARY = "6080604052348015600f57600080fd5b506107af8061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063218751101461005c578063490c8ce2146100715780636c775a8f1461008f578063e79f0e96146100ba578063ee82ff8f146100cd575b600080fd5b61006f61006a3660046103a5565b6100e0565b005b610079610168565b60405161008691906103c2565b60405180910390f35b6100a261009d3660046104d4565b610178565b6040516001600160a01b039091168152602001610086565b6100796100c83660046103a5565b6101a1565b6100a26100db366004610524565b61026c565b336000908152602081905260408082208151600162c3e57160e01b0319815291516101659391926001600160a01b0386169263ff3c1a8f926004808401938290030181865afa158015610137573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261015f9190810190610585565b8361027e565b50565b6060610173336101a1565b905090565b6001600160a01b038216600090815260208190526040812061019a908361031e565b9392505050565b6001600160a01b0381166000908152602081905260408120600101546060918167ffffffffffffffff8111156101d9576101d961040f565b604051908082528060200260200182016040528015610202578160200160208202803683370190505b50905060005b82811015610264576001600160a01b03851660009081526020819052604090206102329082610379565b828281518110610244576102446105fc565b6001600160a01b0390921660209283029190910190910152600101610208565b509392505050565b60006102783383610178565b92915050565b60018084018054604080518082019091528581526001600160a01b03851660208083019190915293820183556000928352929091208251919291600284029091019081906102cc908261069d565b5060209190910151600190910180546001600160a01b0319166001600160a01b039092169190911790556040518190859061030890869061075d565b9081526040519081900360200190205550505050565b6000826001018360000183604051610336919061075d565b90815260200160405180910390205481548110610355576103556105fc565b60009182526020909120600290910201600101546001600160a01b03169392505050565b6000826001018281548110610355576103556105fc565b6001600160a01b038116811461016557600080fd5b6000602082840312156103b757600080fd5b813561019a81610390565b6020808252825182820181905260009190848201906040850190845b818110156104035783516001600160a01b0316835292840192918401916001016103de565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561044e5761044e61040f565b604052919050565b600067ffffffffffffffff8211156104705761047061040f565b50601f01601f191660200190565b600082601f83011261048f57600080fd5b81356104a261049d82610456565b610425565b8181528460208386010111156104b757600080fd5b816020850160208301376000918101602001919091529392505050565b600080604083850312156104e757600080fd5b82356104f281610390565b9150602083013567ffffffffffffffff81111561050e57600080fd5b61051a8582860161047e565b9150509250929050565b60006020828403121561053657600080fd5b813567ffffffffffffffff81111561054d57600080fd5b6105598482850161047e565b949350505050565b60005b8381101561057c578181015183820152602001610564565b50506000910152565b60006020828403121561059757600080fd5b815167ffffffffffffffff8111156105ae57600080fd5b8201601f810184136105bf57600080fd5b80516105cd61049d82610456565b8181528560208385010111156105e257600080fd5b6105f3826020830160208601610561565b95945050505050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061062657607f821691505b60208210810361064657634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610698576000816000526020600020601f850160051c810160208610156106755750805b601f850160051c820191505b8181101561069457828155600101610681565b5050505b505050565b815167ffffffffffffffff8111156106b7576106b761040f565b6106cb816106c58454610612565b8461064c565b602080601f83116001811461070057600084156106e85750858301515b600019600386901b1c1916600185901b178555610694565b600085815260208120601f198616915b8281101561072f57888601518255948401946001909101908401610710565b508582101561074d5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b6000825161076f818460208701610561565b919091019291505056fea2646970667358221220b842360cd0477ce402278bba75075c5559874cedb1e7380dd2e57cca2a6d312764736f6c63430008190033";

    public static final String FUNC_ADDRIGHT = "addRight";

    public static final String FUNC_getRight = "getRight";

    public static final String FUNC_getRights = "getRights";

    @Deprecated
    protected DRManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DRManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DRManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DRManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addRight(String right) {
        final Function function = new Function(
                FUNC_ADDRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, right)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getRight(String owner, String rightName) {
        final Function function = new Function(FUNC_getRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Utf8String(rightName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getRight(String rightName) {
        final Function function = new Function(FUNC_getRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rightName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getRights() {
        final Function function = new Function(FUNC_getRights, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getRights(String owner) {
        final Function function = new Function(FUNC_getRights, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static DRManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DRManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DRManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DRManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DRManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DRManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DRManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DRManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DRManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DRManager.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DRManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DRManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DRManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DRManager.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DRManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DRManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
