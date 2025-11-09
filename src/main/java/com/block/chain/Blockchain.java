package com.block.chain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int difficulty = 4;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        Block genesisBlock = new Block(0, "제네시스 블록 데이터", "0");
        genesisBlock.mineBlock(difficulty);
        return genesisBlock;
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("현재 블록의 해시가 유효하지 않아!");
                return false;
            }

            // 이전 블록의 해시와 일치하는지
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("이전 블록 해시가 일치하지 않아!");
                return false;
            }
        }
        return true;
    }

    // 블록체인 출력 메서드 (콘솔 또는 API로 제공)
    public List<Block> getChain() {
        return chain;
    }
}