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
    public static final String BINARY = "6080604052348015600f57600080fd5b506106cf8061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c8063218751101461004657806348e02aff1461005b578063ee82ff8f14610079575b600080fd5b610059610054366004610315565b6100a4565b005b61006361012c565b6040516100709190610345565b60405180910390f35b61008c610087366004610401565b6101e4565b6040516001600160a01b039091168152602001610070565b336000908152602081905260408082208151600162c3e57160e01b0319815291516101299391926001600160a01b0386169263ff3c1a8f926004808401938290030181865afa1580156100fb573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261012391908101906104a5565b83610203565b50565b336000908152602081905260408120600101546060918167ffffffffffffffff81111561015b5761015b610392565b604051908082528060200260200182016040528015610184578160200160208202803683370190505b50905060005b828110156101dd573360009081526020819052604090206101ab90826102a3565b8282815181106101bd576101bd61051c565b6001600160a01b039092166020928302919091019091015260010161018a565b5092915050565b3360009081526020819052604081206101fd90836102de565b92915050565b60018084018054604080518082019091528581526001600160a01b038516602080830191909152938201835560009283529290912082519192916002840290910190819061025190826105bd565b5060209190910151600190910180546001600160a01b0319166001600160a01b039092169190911790556040518190859061028d90869061067d565b9081526040519081900360200190205550505050565b60008260010182815481106102ba576102ba61051c565b60009182526020909120600290910201600101546001600160a01b03169392505050565b60008260010183600001836040516102f6919061067d565b908152602001604051809103902054815481106102ba576102ba61051c565b60006020828403121561032757600080fd5b81356001600160a01b038116811461033e57600080fd5b9392505050565b6020808252825182820181905260009190848201906040850190845b818110156103865783516001600160a01b031683529284019291840191600101610361565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156103d1576103d1610392565b604052919050565b600067ffffffffffffffff8211156103f3576103f3610392565b50601f01601f191660200190565b60006020828403121561041357600080fd5b813567ffffffffffffffff81111561042a57600080fd5b8201601f8101841361043b57600080fd5b803561044e610449826103d9565b6103a8565b81815285602083850101111561046357600080fd5b81602084016020830137600091810160200191909152949350505050565b60005b8381101561049c578181015183820152602001610484565b50506000910152565b6000602082840312156104b757600080fd5b815167ffffffffffffffff8111156104ce57600080fd5b8201601f810184136104df57600080fd5b80516104ed610449826103d9565b81815285602083850101111561050257600080fd5b610513826020830160208601610481565b95945050505050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061054657607f821691505b60208210810361056657634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156105b8576000816000526020600020601f850160051c810160208610156105955750805b601f850160051c820191505b818110156105b4578281556001016105a1565b5050505b505050565b815167ffffffffffffffff8111156105d7576105d7610392565b6105eb816105e58454610532565b8461056c565b602080601f83116001811461062057600084156106085750858301515b600019600386901b1c1916600185901b1785556105b4565b600085815260208120601f198616915b8281101561064f57888601518255948401946001909101908401610630565b508582101561066d5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b6000825161068f818460208701610481565b919091019291505056fea264697066735822122083299258fd11fbb8c50a331b742746d492c1299d30642f5c676d16edf423e04164736f6c63430008190033";

    public static final String FUNC_ADDRIGHT = "addRight";

    public static final String FUNC_GETALLRIGHTS = "getAllRights";

    public static final String FUNC_GETRIGHT = "getRight";

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

    public RemoteFunctionCall<List> getAllRights() {
        final Function function = new Function(FUNC_GETALLRIGHTS, 
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

    public RemoteFunctionCall<String> getRight(String rightName) {
        final Function function = new Function(FUNC_GETRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rightName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
